#!/usr/bin/env bash

main() {
  uname -a
  test
}

test() {
  ./gradlew --stop
  echo
  echo "# Run task :publishToMavenLocal"
  EXCLUDES_SAMPLES=true ./gradlew clean projects publishToMavenLocal -Pversion=-SNAPSHOT --stacktrace
  echo
  echo "# Assemble samples"
  ./gradlew projects assemble -Pversion=-SNAPSHOT --stacktrace
}

main "$@"
