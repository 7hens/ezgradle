#!/usr/bin/env bash

main() {
  clear
  uname -a
  publish_to_local
  test_samples
}

publish_to_local() {
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

test_samples() {
  if gradle_has_task ":sample-android-app:generateBuildConfig"; then
    echo "error(sample-android-app): generateBuildConfig should not be registered"
    return 1
  fi
  if gradle_has_task ":sample-android-lib:generateBuildConfig"; then
    echo "error(sample-android-lib): generateBuildConfig should not be registered"
    return 1
  fi
  if [ "$(gradle_has_task ":sample-java-lib:generateBuildConfig"; echo $?)" != 0 ]; then
    echo "error(sample-java-lib): generateBuildConfig should be registered"
    return 1
  fi
  echo "Assemble samples"
  ./gradlew projects assemble -Pversion=-SNAPSHOT
  printf "Elapsed Time: %dm %ds\n" $((SECONDS / 60)) $((SECONDS % 60))
}

gradle_has_task() {
  ./gradlew "$1" -m >/dev/null 2>/dev/null
  return $?
}

main "$@"
