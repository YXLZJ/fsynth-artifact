cmake_minimum_required(VERSION 3.18)
project(dot_parser LANGUAGES CXX)

find_package(antlr4-runtime REQUIRED CONFIG)

set(GENERATED_SRC
    DOTBaseListener.cpp
    DOTLexer.cpp
    DOTListener.cpp
    DOTParser.cpp
)

add_executable(dot_parser
    main.cpp
    ${GENERATED_SRC}
)

# Link against the imported target from find_package.
# This automatically handles include directories and library paths.
target_link_libraries(dot_parser PRIVATE antlr4_static)
target_include_directories(dot_parser PRIVATE /usr/local/include/antlr4-runtime)

target_compile_features(dot_parser PRIVATE cxx_std_17)
