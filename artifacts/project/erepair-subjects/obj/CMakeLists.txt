cmake_minimum_required(VERSION 3.18)
project(obj_parser LANGUAGES CXX)

find_package(antlr4-runtime REQUIRED CONFIG)

set(GENERATED_SRC
    WavefrontOBJBaseListener.cpp
    WavefrontOBJLexer.cpp
    WavefrontOBJListener.cpp
    WavefrontOBJParser.cpp
)

add_executable(obj_parser
    main.cpp
    ${GENERATED_SRC}
)

# Link against the imported target from find_package.
# This automatically handles include directories and library paths.
target_link_libraries(obj_parser PRIVATE antlr4_static)
target_include_directories(obj_parser PRIVATE /usr/local/include/antlr4-runtime)

target_compile_features(obj_parser PRIVATE cxx_std_17)
