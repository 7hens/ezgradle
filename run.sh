#!/usr/bin/env bash

main() {
  set -e
  local cmd="$1"
  shift
  local run_cmd="run_$cmd"
  local py_file=".devops/$cmd.py"
  if [ "$(type -t "$run_cmd")" = function ]; then
    "$run_cmd" "$@"
  elif [ -f "$py_file" ]; then
    run_py "$py_file" "$@"
  else
    echo "Unrecognized command: $*"
  fi
}

run_py() {
  run_py_pipenv "$@"
}

run_py_pipenv() {
  command -v pipenv >/dev/nul || pip install --user pipenv
  pipenv install > /dev/nul
  pipenv run python "$@"
}

run_py_venv() {
    run_venv pip install -r requirements.txt > /dev/nul
    run_venv python "$@"
}

run_venv() {
    local cmd="$1"
    shift
    local script
    if x_is_win; then
        script=".venv/Scripts/$cmd"
    else
        script=".venv/bin/$cmd"
    fi
    python -m venv .venv
    chmod +x "$script"
    "$script" "$@"
}

run_ezgradle() {
  local test_name="me.thens.ezgradle.EzGradleRunTest.run"
  EZGRADLE_ARGS="$*" ./gradlew :ezgradle-run:test --tests "$test_name" --rerun
}

run_test() {
  for f in .devops/test_*.py; do
    run_py "$f" "$@"
  done
  run_publish
  run_test_samples
}

run_publish() {
  echo "Run task :publishToMavenLocal"
  local group=com.github.7hens.ezgradle
  local repo_dir=~/.m2/repository/${group//.//}
  rm -rf ~/.gradle/caches/modules*/files*/$group*
  rm -rf "$repo_dir"
  ./gradlew --stop
  EXCLUDES_SAMPLES=true ./gradlew clean projects \
    -Pversion=-SNAPSHOT -xtest publishToMavenLocal --stacktrace
  ls -d $repo_dir/$group.gradle.plugin*
  ls -d $repo_dir/ezgradle-bom*
  printf "Elapsed Time: %dm %ds\n" $((SECONDS / 60)) $((SECONDS % 60))
}

run_test_samples() {
  echo "Assemble samples"
  MODULES='*' ./gradlew projects assemble -Pversion=-SNAPSHOT
  printf "Elapsed Time: %dm %ds\n" $((SECONDS / 60)) $((SECONDS % 60))
}

gradle_has_task() {
  if MODULES='*' ./gradlew "$1" -m >/dev/null 2>/dev/null; then
    echo 1
  else
    echo 0
  fi
}

check_gradle_task() {
  if [ "$(gradle_has_task ":sample-android-app:generateBuildConfig")" == 1 ]; then
    echo "error(sample-android-app): generateBuildConfig should not be registered"
    exit 1
  fi
  if [ "$(gradle_has_task ":sample-android-lib:generateBuildConfig")" == 1 ]; then
    echo "error(sample-android-lib): generateBuildConfig should not be registered"
    exit 1
  fi
  if [ "$(gradle_has_task ":sample-java-lib:generateBuildConfig")" == "0" ]; then
    echo "error(sample-java-lib): generateBuildConfig should be registered"
    exit 1
  fi
}

main "$@"
