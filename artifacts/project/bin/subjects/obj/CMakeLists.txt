cmake_minimum_required(VERSION 3.18)
project(obj_parser LANGUAGES CXX)

execute_process(COMMAND brew --prefix antlr4-cpp-runtime
                OUTPUT_VARIABLE ANTLR4_PREFIX
                OUTPUT_STRIP_TRAILING_WHITESPACE
                ERROR_QUIET)
if(ANTLR4_PREFIX)
  list(APPEND CMAKE_PREFIX_PATH "${ANTLR4_PREFIX}/lib/cmake/antlr4-runtime")
endif()

find_package(antlr4-runtime REQUIRED CONFIG)


set(ANTLR_TARGET antlr4_static)

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


target_include_directories(obj_parser PRIVATE
    "${ANTLR4_PREFIX}/include/antlr4-runtime")

target_link_libraries(obj_parser PRIVATE ${ANTLR_TARGET})
target_compile_features(obj_parser PRIVATE cxx_std_17)