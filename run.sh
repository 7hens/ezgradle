#!/usr/bin/env bash

main() {
  set -e
  uname -a
  test_assemble
}

test_assemble() {
  clear
  local group=com.github.7hens.ezgradle
  local groupDir=~/.m2/repository/${group//.//}
  rm -rf ~/.gradle/caches/modules*/files*/$group*
  rm -rf "$groupDir"
  ./gradlew --stop
  echo
  echo "# Run task :publishToMavenLocal"
  EXCLUDES_SAMPLES=true ./gradlew clean projects \
    -Pversion=-SNAPSHOT -xtest assemble publishToMavenLocal --stacktrace
  echo "\$ ls $group.gradle.plugin"
  ls "$groupDir/$group.gradle.plugin"
  echo "\$ ls ezgradle-bom"
  ls "$groupDir/ezgradle-bom"
  echo
  echo "# Assemble samples"
  ./gradlew projects assemble -Pversion=-SNAPSHOT
  printf "Elapsed Time: %dm %ds\n" $[$SECONDS / 60] $[$SECONDS % 60]
}

main "$@"
