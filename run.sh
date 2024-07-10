#!/usr/bin/env bash

#PYTHON_VENV=pipenv
PYTHON_VENV=venv

main() {
  set -e
  local cmd="$1"
  shift
  local run_cmd="run_$cmd"
  local py_file=".devops/run.py"
  if [ "$(type -t "$run_cmd")" = function ]; then
    "$run_cmd" "$@"
  elif [ -f "$py_file" ]; then
    run_py "$py_file" "$cmd" "$@"
  else
    echo "Unrecognized command: $*"
  fi
}

run_py() {
  run_py_$PYTHON_VENV "$@"
}

run_py_pipenv() {
  command -v pipenv >/dev/null || pip install --user pipenv
  pipenv install > /dev/null
  pipenv run python3 "$@"
}

run_py_venv() {
    if [ -f requirements.txt ]; then
        run_venv pip install -r requirements.txt > /dev/null
    fi
    run_venv python3 "$@"
}

run_venv() {
    python3 -m venv .venv
    local cmd="$1"
    shift
    local script=".venv/bin/$cmd"
    if [[ ! -f "$script" ]]; then
        script=".venv/Scripts/$cmd"
    fi
    echo test
#    chmod +x "$script"
    "$script" "$@"
}

run_pip() {
  run_pip_$PYTHON_VENV "$@"
}

run_pip_pipenv() {
  pipenv "$@"
}

run_pip_venv() {
  run_venv pip "$@"
  if [ "$1" = install ]; then
    run_venv pip freeze > requirements.txt
  fi
}

run_ezgradle() {
  local test_name="me.thens.ezgradle.EzGradleRunTest.run"
  EZGRADLE_ARGS="$*" ./gradlew :ezgradle-run:test --tests "$test_name" --rerun
}

run_test() {
  for f in .devops/test_*.py; do
    test -e "$f" || continue
    run_py "$f" "$@"
  done
  run_publish
  run_test_samples
}

run_publish() {
  run_refresh
  echo "Run task :publishToMavenLocal"
  local group=com.gitlab.7hens.ezgradle
  local repo_dir=~/.m2/repository/${group//.//}
  rm -rf ~/.gradle/caches/modules*/files*/$group*
  rm -rf "$repo_dir"
  ./gradlew --stop
  EXCLUDES_SAMPLES=true ./gradlew clean projects \
    -xtest publishToMavenLocal --stacktrace
  ls -d $repo_dir/*/*
  printf "Elapsed Time: %dm %ds\n" $((SECONDS / 60)) $((SECONDS % 60))
}

run_test_samples() {
  echo "Assemble samples"
  MODULES='.' ./gradlew projects assemble -Pversion=-SNAPSHOT --stacktrace
  printf "Elapsed Time: %dm %ds\n" $((SECONDS / 60)) $((SECONDS % 60))
}

run_refresh() {
  local toml=gradle/libs.versions.toml
  local constants=ezgradle-plugin/src/main/java/me/thens/ezgradle/EzGradleConstants.kt
  local ezgradle
  local android_min_sdk
  local android_target_sdk
  local compose_compiler
  ezgradle="$(grep 'version=' gradle.properties | cut -d '=' -f2)"
  android_min_sdk="$(grep 'androidMinSdk' $toml | head -1 | cut -d '=' -f2)"
  android_target_sdk="$(grep 'androidTargetSdk' $toml | head -1 | cut -d '=' -f2)"
  compose_compiler="$(grep 'composeCompiler' $toml | head -1 | cut -d '=' -f2)"
  sed -i'' -e "s/\\(ezgradle = \"\\).*/\\1$ezgradle\"/" $toml
  sed -i'' -e "s/\\(val ezgradleVersion = \\).*/\\1\"$ezgradle\"/" settings.gradle.kts
#  sed -i'' -e "s/\\(ezgradle\". version \\).*/\\1\"$ezgradle\"/" build.gradle.kts
  sed -i'' -e "s/\\(val VERSION = \\).*/\\1\"$ezgradle\"/" "$constants"
  sed -i'' -e "s/\\(val ANDROID_MIN_SDK =\\).*/\\1$android_min_sdk/" "$constants"
  sed -i'' -e "s/\\(val ANDROID_TARGET_SDK =\\).*/\\1$android_target_sdk/" "$constants"
  sed -i'' -e "s/\\(val COMPOSE_COMPILER =\\).*/\\1$compose_compiler/" "$constants"
  main update_bom
}

gradle_has_task() {
  if MODULES='.' ./gradlew "$1" -m >/dev/null 2>/dev/null; then
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
