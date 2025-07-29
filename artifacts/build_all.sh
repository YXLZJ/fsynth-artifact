#!/bin/bash

set -e

# This script is intended to be run inside a Docker container.

BASE_DIR=$(dirname "$0")
SUBJECTS_DIR="$BASE_DIR/project/erepair-subjects"

# Projects with Makefile
MAKE_PROJECTS=("cjson" "csv" "ini" "mjs" "sexp-parser" "tiny" "tri")

for proj in "${MAKE_PROJECTS[@]}"; do
    echo "Cleaning and building $proj..."
    (cd "$SUBJECTS_DIR/$proj" && make clean && make)
    echo "$proj built successfully."
done

# Projects with CMake
CMAKE_PROJECTS=("dot" "obj")

for proj in "${CMAKE_PROJECTS[@]}"; do
    echo "Cleaning and building $proj..."
    PROJ_DIR="$SUBJECTS_DIR/$proj"
    BUILD_DIR="$PROJ_DIR/build"
    if [ -d "$BUILD_DIR" ]; then
        echo "Removing existing build directory for $proj"
        rm -rf "$BUILD_DIR"
    fi
    # Set CMAKE_PREFIX_PATH to help find the manually installed antlr4-runtime
    (cd "$PROJ_DIR" && mkdir -p build && cd build && cmake -DCMAKE_PREFIX_PATH=/usr/local .. && make)
    echo "$proj built successfully."
done

echo "All projects built successfully!"
