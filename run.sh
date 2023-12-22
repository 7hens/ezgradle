#!/usr/bin/env bash

main() {
  uname -a
  test
}

test() {
  local group=com.github.7hens.ezgradle
  local groupDir=~/.m2/repository/${group//.//}
  rm -rf ~/.gradle/caches/modules-2/files-2.1/$group*
  rm -rf "$groupDir"
  ./gradlew --stop
  echo
  echo "# Run task :publishToMavenLocal"
  EXCLUDES_SAMPLES=true ./gradlew clean projects \
    -PVERSION=-SNAPSHOT -xtest assemble publishToMavenLocal --stacktrace
  echo "\$ ls $group.gradle.plugin"
  ls "$groupDir/$group.gradle.plugin"
  echo "\$ ls ezgradle-bom"
  ls "$groupDir/ezgradle-bom"
  echo
  echo "# Assemble samples"
  ./gradlew projects assemble -PVERSION=-SNAPSHOT --stacktrace
}

main "$@"
