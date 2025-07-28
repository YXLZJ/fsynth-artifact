
// Generated from /Users/jack/fsynth-artifact/obj/WavefrontOBJ.g4 by ANTLR 4.13.2


#include "WavefrontOBJListener.h"

#include "WavefrontOBJParser.h"


using namespace antlrcpp;

using namespace antlr4;

namespace {

struct WavefrontOBJParserStaticData final {
  WavefrontOBJParserStaticData(std::vector<std::string> ruleNames,
                        std::vector<std::string> literalNames,
                        std::vector<std::string> symbolicNames)
      : ruleNames(std::move(ruleNames)), literalNames(std::move(literalNames)),
        symbolicNames(std::move(symbolicNames)),
        vocabulary(this->literalNames, this->symbolicNames) {}

  WavefrontOBJParserStaticData(const WavefrontOBJParserStaticData&) = delete;
  WavefrontOBJParserStaticData(WavefrontOBJParserStaticData&&) = delete;
  WavefrontOBJParserStaticData& operator=(const WavefrontOBJParserStaticData&) = delete;
  WavefrontOBJParserStaticData& operator=(WavefrontOBJParserStaticData&&) = delete;

  std::vector<antlr4::dfa::DFA> decisionToDFA;
  antlr4::atn::PredictionContextCache sharedContextCache;
  const std::vector<std::string> ruleNames;
  const std::vector<std::string> literalNames;
  const std::vector<std::string> symbolicNames;
  const antlr4::dfa::Vocabulary vocabulary;
  antlr4::atn::SerializedATNView serializedATN;
  std::unique_ptr<antlr4::atn::ATN> atn;
};

::antlr4::internal::OnceFlag wavefrontobjParserOnceFlag;
#if ANTLR4_USE_THREAD_LOCAL_CACHE
static thread_local
#endif
std::unique_ptr<WavefrontOBJParserStaticData> wavefrontobjParserStaticData = nullptr;

void wavefrontobjParserInitialize() {
#if ANTLR4_USE_THREAD_LOCAL_CACHE
  if (wavefrontobjParserStaticData != nullptr) {
    return;
  }
#else
  assert(wavefrontobjParserStaticData == nullptr);
#endif
  auto staticData = std::make_unique<WavefrontOBJParserStaticData>(
    std::vector<std::string>{
      "start_", "statement", "call", "csh", "vertex", "vertex_parameter", 
      "vertex_normal", "vertex_texture", "curve_surface_type", "degree", 
      "basis_matrix", "step", "points", "lines", "faces", "free_form_surface", 
      "curve", "curve2d", "surface", "parameter", "outer_trimming_loop", 
      "inner_trimming_loop", "special_curve", "special_point", "end", "connectivity", 
      "group", "smoothing_group", "merging_group", "object_name", "bevel", 
      "color_interpolation", "dissolve_interpolation", "level_of_detail", 
      "map_library", "use_map", "material_library", "use_material", "shadow_object", 
      "trace_object", "curve_approximation_technique", "surface_approximation_technique", 
      "decimal"
    },
    std::vector<std::string>{
      "", "'-'", "'v'", "'vp'", "'vn'", "'vt'", "'cstype'", "'rat'", "'bmatrix'", 
      "'bezier'", "'bspline'", "'cardinal'", "'taylor'", "'deg'", "'bmat'", 
      "'u'", "'step'", "'p'", "'l'", "'f'", "'curv'", "'curv2'", "'surf'", 
      "'parm'", "'trim'", "'hole'", "'scrv'", "'sp'", "'end'", "'con'", 
      "'g'", "'s'", "'off'", "'mg'", "'o'", "'bevel'", "'on'", "'c_interp'", 
      "'d_interp'", "'lod'", "'maplib'", "'usemap'", "'mtllib'", "'usemtl'", 
      "'shadow_obj'", "'trace_obj'", "'ctech'", "'cparm'", "'cspace'", "'stech'", 
      "'cparma'", "'cparmb'"
    },
    std::vector<std::string>{
      "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
      "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
      "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 
      "", "INTEGER_PAIR", "INTEGER_TRIPLET", "INTEGER", "DECIMAL", "COMMENT", 
      "NAME", "FILENAME", "WS", "NL", "NON_NL", "NON_WS"
    }
  );
  static const int32_t serializedATNSegment[] = {
  	4,1,62,414,2,0,7,0,2,1,7,1,2,2,7,2,2,3,7,3,2,4,7,4,2,5,7,5,2,6,7,6,2,
  	7,7,7,2,8,7,8,2,9,7,9,2,10,7,10,2,11,7,11,2,12,7,12,2,13,7,13,2,14,7,
  	14,2,15,7,15,2,16,7,16,2,17,7,17,2,18,7,18,2,19,7,19,2,20,7,20,2,21,7,
  	21,2,22,7,22,2,23,7,23,2,24,7,24,2,25,7,25,2,26,7,26,2,27,7,27,2,28,7,
  	28,2,29,7,29,2,30,7,30,2,31,7,31,2,32,7,32,2,33,7,33,2,34,7,34,2,35,7,
  	35,2,36,7,36,2,37,7,37,2,38,7,38,2,39,7,39,2,40,7,40,2,41,7,41,2,42,7,
  	42,1,0,5,0,88,8,0,10,0,12,0,91,9,0,1,0,1,0,4,0,95,8,0,11,0,12,0,96,1,
  	0,3,0,100,8,0,4,0,102,8,0,11,0,12,0,103,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
  	1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
  	1,1,1,1,1,1,1,1,1,1,1,1,3,1,137,8,1,1,2,1,2,5,2,141,8,2,10,2,12,2,144,
  	9,2,1,3,1,3,3,3,148,8,3,1,3,5,3,151,8,3,10,3,12,3,154,9,3,1,4,1,4,1,4,
  	1,4,1,4,3,4,161,8,4,1,5,1,5,1,5,3,5,166,8,5,1,5,3,5,169,8,5,1,6,1,6,1,
  	6,1,6,1,6,1,7,1,7,1,7,3,7,179,8,7,1,7,3,7,182,8,7,1,8,1,8,3,8,186,8,8,
  	1,8,1,8,1,9,1,9,1,9,3,9,193,8,9,1,10,1,10,1,10,4,10,198,8,10,11,10,12,
  	10,199,1,11,1,11,1,11,3,11,205,8,11,1,12,1,12,4,12,209,8,12,11,12,12,
  	12,210,1,13,1,13,4,13,215,8,13,11,13,12,13,216,1,14,1,14,4,14,221,8,14,
  	11,14,12,14,222,1,15,1,15,1,15,3,15,228,8,15,1,15,4,15,231,8,15,11,15,
  	12,15,232,1,15,1,15,1,15,1,15,1,15,3,15,240,8,15,1,15,4,15,243,8,15,11,
  	15,12,15,244,5,15,247,8,15,10,15,12,15,250,9,15,1,15,1,15,1,16,1,16,1,
  	16,1,16,4,16,258,8,16,11,16,12,16,259,1,17,1,17,1,17,4,17,265,8,17,11,
  	17,12,17,266,1,18,1,18,1,18,1,18,1,18,1,18,4,18,275,8,18,11,18,12,18,
  	276,1,19,1,19,1,19,4,19,282,8,19,11,19,12,19,283,1,20,1,20,1,20,1,20,
  	1,20,4,20,291,8,20,11,20,12,20,292,1,21,1,21,1,21,1,21,1,21,4,21,300,
  	8,21,11,21,12,21,301,1,22,1,22,1,22,1,22,1,22,4,22,309,8,22,11,22,12,
  	22,310,1,23,1,23,4,23,315,8,23,11,23,12,23,316,1,24,1,24,1,25,1,25,1,
  	25,1,25,1,25,1,25,1,25,1,25,1,25,1,25,1,26,1,26,4,26,333,8,26,11,26,12,
  	26,334,1,27,1,27,1,27,3,27,340,8,27,1,28,1,28,1,28,1,28,3,28,346,8,28,
  	1,29,1,29,1,29,1,30,1,30,1,30,1,31,1,31,1,31,1,32,1,32,1,32,1,33,1,33,
  	1,33,1,34,1,34,4,34,365,8,34,11,34,12,34,366,1,35,1,35,1,35,3,35,372,
  	8,35,1,36,1,36,1,36,1,37,1,37,1,37,1,38,1,38,1,38,1,39,1,39,1,39,1,40,
  	1,40,1,40,1,40,1,40,1,40,1,40,1,40,1,40,3,40,395,8,40,1,41,1,41,1,41,
  	1,41,1,41,1,41,1,41,1,41,1,41,1,41,1,41,1,41,1,41,3,41,410,8,41,1,42,
  	1,42,1,42,0,0,43,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,
  	38,40,42,44,46,48,50,52,54,56,58,60,62,64,66,68,70,72,74,76,78,80,82,
  	84,0,6,1,0,8,12,2,0,2,2,15,15,2,0,52,52,54,54,1,0,52,54,2,0,32,32,36,
  	36,1,0,54,55,446,0,89,1,0,0,0,2,136,1,0,0,0,4,138,1,0,0,0,6,145,1,0,0,
  	0,8,155,1,0,0,0,10,162,1,0,0,0,12,170,1,0,0,0,14,175,1,0,0,0,16,183,1,
  	0,0,0,18,189,1,0,0,0,20,194,1,0,0,0,22,201,1,0,0,0,24,206,1,0,0,0,26,
  	212,1,0,0,0,28,218,1,0,0,0,30,227,1,0,0,0,32,253,1,0,0,0,34,261,1,0,0,
  	0,36,268,1,0,0,0,38,278,1,0,0,0,40,285,1,0,0,0,42,294,1,0,0,0,44,303,
  	1,0,0,0,46,312,1,0,0,0,48,318,1,0,0,0,50,320,1,0,0,0,52,330,1,0,0,0,54,
  	336,1,0,0,0,56,341,1,0,0,0,58,347,1,0,0,0,60,350,1,0,0,0,62,353,1,0,0,
  	0,64,356,1,0,0,0,66,359,1,0,0,0,68,362,1,0,0,0,70,368,1,0,0,0,72,373,
  	1,0,0,0,74,376,1,0,0,0,76,379,1,0,0,0,78,382,1,0,0,0,80,385,1,0,0,0,82,
  	396,1,0,0,0,84,411,1,0,0,0,86,88,5,60,0,0,87,86,1,0,0,0,88,91,1,0,0,0,
  	89,87,1,0,0,0,89,90,1,0,0,0,90,101,1,0,0,0,91,89,1,0,0,0,92,99,3,2,1,
  	0,93,95,5,60,0,0,94,93,1,0,0,0,95,96,1,0,0,0,96,94,1,0,0,0,96,97,1,0,
  	0,0,97,100,1,0,0,0,98,100,5,0,0,1,99,94,1,0,0,0,99,98,1,0,0,0,100,102,
  	1,0,0,0,101,92,1,0,0,0,102,103,1,0,0,0,103,101,1,0,0,0,103,104,1,0,0,
  	0,104,1,1,0,0,0,105,137,3,4,2,0,106,137,3,6,3,0,107,137,3,8,4,0,108,137,
  	3,12,6,0,109,137,3,14,7,0,110,137,3,10,5,0,111,137,3,24,12,0,112,137,
  	3,26,13,0,113,137,3,28,14,0,114,137,3,16,8,0,115,137,3,18,9,0,116,137,
  	3,20,10,0,117,137,3,22,11,0,118,137,3,30,15,0,119,137,3,50,25,0,120,137,
  	3,52,26,0,121,137,3,54,27,0,122,137,3,56,28,0,123,137,3,58,29,0,124,137,
  	3,60,30,0,125,137,3,62,31,0,126,137,3,64,32,0,127,137,3,66,33,0,128,137,
  	3,68,34,0,129,137,3,70,35,0,130,137,3,72,36,0,131,137,3,74,37,0,132,137,
  	3,76,38,0,133,137,3,78,39,0,134,137,3,80,40,0,135,137,3,82,41,0,136,105,
  	1,0,0,0,136,106,1,0,0,0,136,107,1,0,0,0,136,108,1,0,0,0,136,109,1,0,0,
  	0,136,110,1,0,0,0,136,111,1,0,0,0,136,112,1,0,0,0,136,113,1,0,0,0,136,
  	114,1,0,0,0,136,115,1,0,0,0,136,116,1,0,0,0,136,117,1,0,0,0,136,118,1,
  	0,0,0,136,119,1,0,0,0,136,120,1,0,0,0,136,121,1,0,0,0,136,122,1,0,0,0,
  	136,123,1,0,0,0,136,124,1,0,0,0,136,125,1,0,0,0,136,126,1,0,0,0,136,127,
  	1,0,0,0,136,128,1,0,0,0,136,129,1,0,0,0,136,130,1,0,0,0,136,131,1,0,0,
  	0,136,132,1,0,0,0,136,133,1,0,0,0,136,134,1,0,0,0,136,135,1,0,0,0,137,
  	3,1,0,0,0,138,142,5,58,0,0,139,141,5,62,0,0,140,139,1,0,0,0,141,144,1,
  	0,0,0,142,140,1,0,0,0,142,143,1,0,0,0,143,5,1,0,0,0,144,142,1,0,0,0,145,
  	147,5,58,0,0,146,148,5,1,0,0,147,146,1,0,0,0,147,148,1,0,0,0,148,152,
  	1,0,0,0,149,151,5,62,0,0,150,149,1,0,0,0,151,154,1,0,0,0,152,150,1,0,
  	0,0,152,153,1,0,0,0,153,7,1,0,0,0,154,152,1,0,0,0,155,156,5,2,0,0,156,
  	157,3,84,42,0,157,158,3,84,42,0,158,160,3,84,42,0,159,161,3,84,42,0,160,
  	159,1,0,0,0,160,161,1,0,0,0,161,9,1,0,0,0,162,163,5,3,0,0,163,165,3,84,
  	42,0,164,166,3,84,42,0,165,164,1,0,0,0,165,166,1,0,0,0,166,168,1,0,0,
  	0,167,169,3,84,42,0,168,167,1,0,0,0,168,169,1,0,0,0,169,11,1,0,0,0,170,
  	171,5,4,0,0,171,172,3,84,42,0,172,173,3,84,42,0,173,174,3,84,42,0,174,
  	13,1,0,0,0,175,176,5,5,0,0,176,178,3,84,42,0,177,179,3,84,42,0,178,177,
  	1,0,0,0,178,179,1,0,0,0,179,181,1,0,0,0,180,182,3,84,42,0,181,180,1,0,
  	0,0,181,182,1,0,0,0,182,15,1,0,0,0,183,185,5,6,0,0,184,186,5,7,0,0,185,
  	184,1,0,0,0,185,186,1,0,0,0,186,187,1,0,0,0,187,188,7,0,0,0,188,17,1,
  	0,0,0,189,190,5,13,0,0,190,192,5,54,0,0,191,193,5,54,0,0,192,191,1,0,
  	0,0,192,193,1,0,0,0,193,19,1,0,0,0,194,195,5,14,0,0,195,197,7,1,0,0,196,
  	198,3,84,42,0,197,196,1,0,0,0,198,199,1,0,0,0,199,197,1,0,0,0,199,200,
  	1,0,0,0,200,21,1,0,0,0,201,202,5,16,0,0,202,204,5,54,0,0,203,205,5,54,
  	0,0,204,203,1,0,0,0,204,205,1,0,0,0,205,23,1,0,0,0,206,208,5,17,0,0,207,
  	209,5,54,0,0,208,207,1,0,0,0,209,210,1,0,0,0,210,208,1,0,0,0,210,211,
  	1,0,0,0,211,25,1,0,0,0,212,214,5,18,0,0,213,215,7,2,0,0,214,213,1,0,0,
  	0,215,216,1,0,0,0,216,214,1,0,0,0,216,217,1,0,0,0,217,27,1,0,0,0,218,
  	220,5,19,0,0,219,221,7,3,0,0,220,219,1,0,0,0,221,222,1,0,0,0,222,220,
  	1,0,0,0,222,223,1,0,0,0,223,29,1,0,0,0,224,228,3,32,16,0,225,228,3,34,
  	17,0,226,228,3,36,18,0,227,224,1,0,0,0,227,225,1,0,0,0,227,226,1,0,0,
  	0,228,230,1,0,0,0,229,231,5,60,0,0,230,229,1,0,0,0,231,232,1,0,0,0,232,
  	230,1,0,0,0,232,233,1,0,0,0,233,248,1,0,0,0,234,240,3,38,19,0,235,240,
  	3,40,20,0,236,240,3,42,21,0,237,240,3,44,22,0,238,240,3,46,23,0,239,234,
  	1,0,0,0,239,235,1,0,0,0,239,236,1,0,0,0,239,237,1,0,0,0,239,238,1,0,0,
  	0,240,242,1,0,0,0,241,243,5,60,0,0,242,241,1,0,0,0,243,244,1,0,0,0,244,
  	242,1,0,0,0,244,245,1,0,0,0,245,247,1,0,0,0,246,239,1,0,0,0,247,250,1,
  	0,0,0,248,246,1,0,0,0,248,249,1,0,0,0,249,251,1,0,0,0,250,248,1,0,0,0,
  	251,252,3,48,24,0,252,31,1,0,0,0,253,254,5,20,0,0,254,255,3,84,42,0,255,
  	257,3,84,42,0,256,258,5,54,0,0,257,256,1,0,0,0,258,259,1,0,0,0,259,257,
  	1,0,0,0,259,260,1,0,0,0,260,33,1,0,0,0,261,262,5,21,0,0,262,264,5,54,
  	0,0,263,265,5,54,0,0,264,263,1,0,0,0,265,266,1,0,0,0,266,264,1,0,0,0,
  	266,267,1,0,0,0,267,35,1,0,0,0,268,269,5,22,0,0,269,270,3,84,42,0,270,
  	271,3,84,42,0,271,272,3,84,42,0,272,274,3,84,42,0,273,275,7,3,0,0,274,
  	273,1,0,0,0,275,276,1,0,0,0,276,274,1,0,0,0,276,277,1,0,0,0,277,37,1,
  	0,0,0,278,279,5,23,0,0,279,281,7,1,0,0,280,282,3,84,42,0,281,280,1,0,
  	0,0,282,283,1,0,0,0,283,281,1,0,0,0,283,284,1,0,0,0,284,39,1,0,0,0,285,
  	290,5,24,0,0,286,287,3,84,42,0,287,288,3,84,42,0,288,289,5,54,0,0,289,
  	291,1,0,0,0,290,286,1,0,0,0,291,292,1,0,0,0,292,290,1,0,0,0,292,293,1,
  	0,0,0,293,41,1,0,0,0,294,299,5,25,0,0,295,296,3,84,42,0,296,297,3,84,
  	42,0,297,298,5,54,0,0,298,300,1,0,0,0,299,295,1,0,0,0,300,301,1,0,0,0,
  	301,299,1,0,0,0,301,302,1,0,0,0,302,43,1,0,0,0,303,308,5,26,0,0,304,305,
  	3,84,42,0,305,306,3,84,42,0,306,307,5,54,0,0,307,309,1,0,0,0,308,304,
  	1,0,0,0,309,310,1,0,0,0,310,308,1,0,0,0,310,311,1,0,0,0,311,45,1,0,0,
  	0,312,314,5,27,0,0,313,315,5,54,0,0,314,313,1,0,0,0,315,316,1,0,0,0,316,
  	314,1,0,0,0,316,317,1,0,0,0,317,47,1,0,0,0,318,319,5,28,0,0,319,49,1,
  	0,0,0,320,321,5,29,0,0,321,322,5,54,0,0,322,323,3,84,42,0,323,324,3,84,
  	42,0,324,325,5,54,0,0,325,326,5,54,0,0,326,327,3,84,42,0,327,328,3,84,
  	42,0,328,329,5,54,0,0,329,51,1,0,0,0,330,332,5,30,0,0,331,333,5,57,0,
  	0,332,331,1,0,0,0,333,334,1,0,0,0,334,332,1,0,0,0,334,335,1,0,0,0,335,
  	53,1,0,0,0,336,339,5,31,0,0,337,340,5,54,0,0,338,340,5,32,0,0,339,337,
  	1,0,0,0,339,338,1,0,0,0,340,55,1,0,0,0,341,345,5,33,0,0,342,343,5,54,
  	0,0,343,346,3,84,42,0,344,346,5,32,0,0,345,342,1,0,0,0,345,344,1,0,0,
  	0,346,57,1,0,0,0,347,348,5,34,0,0,348,349,5,57,0,0,349,59,1,0,0,0,350,
  	351,5,35,0,0,351,352,7,4,0,0,352,61,1,0,0,0,353,354,5,37,0,0,354,355,
  	7,4,0,0,355,63,1,0,0,0,356,357,5,38,0,0,357,358,7,4,0,0,358,65,1,0,0,
  	0,359,360,5,39,0,0,360,361,5,54,0,0,361,67,1,0,0,0,362,364,5,40,0,0,363,
  	365,5,58,0,0,364,363,1,0,0,0,365,366,1,0,0,0,366,364,1,0,0,0,366,367,
  	1,0,0,0,367,69,1,0,0,0,368,371,5,41,0,0,369,372,5,57,0,0,370,372,5,32,
  	0,0,371,369,1,0,0,0,371,370,1,0,0,0,372,71,1,0,0,0,373,374,5,42,0,0,374,
  	375,5,58,0,0,375,73,1,0,0,0,376,377,5,43,0,0,377,378,5,57,0,0,378,75,
  	1,0,0,0,379,380,5,44,0,0,380,381,5,58,0,0,381,77,1,0,0,0,382,383,5,45,
  	0,0,383,384,5,58,0,0,384,79,1,0,0,0,385,394,5,46,0,0,386,387,5,47,0,0,
  	387,395,3,84,42,0,388,389,5,48,0,0,389,395,3,84,42,0,390,391,5,20,0,0,
  	391,392,3,84,42,0,392,393,3,84,42,0,393,395,1,0,0,0,394,386,1,0,0,0,394,
  	388,1,0,0,0,394,390,1,0,0,0,395,81,1,0,0,0,396,409,5,49,0,0,397,398,5,
  	50,0,0,398,399,3,84,42,0,399,400,3,84,42,0,400,410,1,0,0,0,401,402,5,
  	51,0,0,402,410,3,84,42,0,403,404,5,48,0,0,404,410,3,84,42,0,405,406,5,
  	20,0,0,406,407,3,84,42,0,407,408,3,84,42,0,408,410,1,0,0,0,409,397,1,
  	0,0,0,409,401,1,0,0,0,409,403,1,0,0,0,409,405,1,0,0,0,410,83,1,0,0,0,
  	411,412,7,5,0,0,412,85,1,0,0,0,40,89,96,99,103,136,142,147,152,160,165,
  	168,178,181,185,192,199,204,210,216,222,227,232,239,244,248,259,266,276,
  	283,292,301,310,316,334,339,345,366,371,394,409
  };
  staticData->serializedATN = antlr4::atn::SerializedATNView(serializedATNSegment, sizeof(serializedATNSegment) / sizeof(serializedATNSegment[0]));

  antlr4::atn::ATNDeserializer deserializer;
  staticData->atn = deserializer.deserialize(staticData->serializedATN);

  const size_t count = staticData->atn->getNumberOfDecisions();
  staticData->decisionToDFA.reserve(count);
  for (size_t i = 0; i < count; i++) { 
    staticData->decisionToDFA.emplace_back(staticData->atn->getDecisionState(i), i);
  }
  wavefrontobjParserStaticData = std::move(staticData);
}

}

WavefrontOBJParser::WavefrontOBJParser(TokenStream *input) : WavefrontOBJParser(input, antlr4::atn::ParserATNSimulatorOptions()) {}

WavefrontOBJParser::WavefrontOBJParser(TokenStream *input, const antlr4::atn::ParserATNSimulatorOptions &options) : Parser(input) {
  WavefrontOBJParser::initialize();
  _interpreter = new atn::ParserATNSimulator(this, *wavefrontobjParserStaticData->atn, wavefrontobjParserStaticData->decisionToDFA, wavefrontobjParserStaticData->sharedContextCache, options);
}

WavefrontOBJParser::~WavefrontOBJParser() {
  delete _interpreter;
}

const atn::ATN& WavefrontOBJParser::getATN() const {
  return *wavefrontobjParserStaticData->atn;
}

std::string WavefrontOBJParser::getGrammarFileName() const {
  return "WavefrontOBJ.g4";
}

const std::vector<std::string>& WavefrontOBJParser::getRuleNames() const {
  return wavefrontobjParserStaticData->ruleNames;
}

const dfa::Vocabulary& WavefrontOBJParser::getVocabulary() const {
  return wavefrontobjParserStaticData->vocabulary;
}

antlr4::atn::SerializedATNView WavefrontOBJParser::getSerializedATN() const {
  return wavefrontobjParserStaticData->serializedATN;
}


//----------------- Start_Context ------------------------------------------------------------------

WavefrontOBJParser::Start_Context::Start_Context(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Start_Context::NL() {
  return getTokens(WavefrontOBJParser::NL);
}

tree::TerminalNode* WavefrontOBJParser::Start_Context::NL(size_t i) {
  return getToken(WavefrontOBJParser::NL, i);
}

std::vector<WavefrontOBJParser::StatementContext *> WavefrontOBJParser::Start_Context::statement() {
  return getRuleContexts<WavefrontOBJParser::StatementContext>();
}

WavefrontOBJParser::StatementContext* WavefrontOBJParser::Start_Context::statement(size_t i) {
  return getRuleContext<WavefrontOBJParser::StatementContext>(i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Start_Context::EOF() {
  return getTokens(WavefrontOBJParser::EOF);
}

tree::TerminalNode* WavefrontOBJParser::Start_Context::EOF(size_t i) {
  return getToken(WavefrontOBJParser::EOF, i);
}


size_t WavefrontOBJParser::Start_Context::getRuleIndex() const {
  return WavefrontOBJParser::RuleStart_;
}

void WavefrontOBJParser::Start_Context::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterStart_(this);
}

void WavefrontOBJParser::Start_Context::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitStart_(this);
}

WavefrontOBJParser::Start_Context* WavefrontOBJParser::start_() {
  Start_Context *_localctx = _tracker.createInstance<Start_Context>(_ctx, getState());
  enterRule(_localctx, 0, WavefrontOBJParser::RuleStart_);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(89);
    _errHandler->sync(this);
    _la = _input->LA(1);
    while (_la == WavefrontOBJParser::NL) {
      setState(86);
      match(WavefrontOBJParser::NL);
      setState(91);
      _errHandler->sync(this);
      _la = _input->LA(1);
    }
    setState(101); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(92);
      statement();
      setState(99);
      _errHandler->sync(this);
      switch (_input->LA(1)) {
        case WavefrontOBJParser::NL: {
          setState(94); 
          _errHandler->sync(this);
          _la = _input->LA(1);
          do {
            setState(93);
            match(WavefrontOBJParser::NL);
            setState(96); 
            _errHandler->sync(this);
            _la = _input->LA(1);
          } while (_la == WavefrontOBJParser::NL);
          break;
        }

        case WavefrontOBJParser::EOF: {
          setState(98);
          match(WavefrontOBJParser::EOF);
          break;
        }

      default:
        throw NoViableAltException(this);
      }
      setState(103); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while ((((_la & ~ 0x3fULL) == 0) &&
      ((1ULL << _la) & 288933990050521212) != 0));
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- StatementContext ------------------------------------------------------------------

WavefrontOBJParser::StatementContext::StatementContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

WavefrontOBJParser::CallContext* WavefrontOBJParser::StatementContext::call() {
  return getRuleContext<WavefrontOBJParser::CallContext>(0);
}

WavefrontOBJParser::CshContext* WavefrontOBJParser::StatementContext::csh() {
  return getRuleContext<WavefrontOBJParser::CshContext>(0);
}

WavefrontOBJParser::VertexContext* WavefrontOBJParser::StatementContext::vertex() {
  return getRuleContext<WavefrontOBJParser::VertexContext>(0);
}

WavefrontOBJParser::Vertex_normalContext* WavefrontOBJParser::StatementContext::vertex_normal() {
  return getRuleContext<WavefrontOBJParser::Vertex_normalContext>(0);
}

WavefrontOBJParser::Vertex_textureContext* WavefrontOBJParser::StatementContext::vertex_texture() {
  return getRuleContext<WavefrontOBJParser::Vertex_textureContext>(0);
}

WavefrontOBJParser::Vertex_parameterContext* WavefrontOBJParser::StatementContext::vertex_parameter() {
  return getRuleContext<WavefrontOBJParser::Vertex_parameterContext>(0);
}

WavefrontOBJParser::PointsContext* WavefrontOBJParser::StatementContext::points() {
  return getRuleContext<WavefrontOBJParser::PointsContext>(0);
}

WavefrontOBJParser::LinesContext* WavefrontOBJParser::StatementContext::lines() {
  return getRuleContext<WavefrontOBJParser::LinesContext>(0);
}

WavefrontOBJParser::FacesContext* WavefrontOBJParser::StatementContext::faces() {
  return getRuleContext<WavefrontOBJParser::FacesContext>(0);
}

WavefrontOBJParser::Curve_surface_typeContext* WavefrontOBJParser::StatementContext::curve_surface_type() {
  return getRuleContext<WavefrontOBJParser::Curve_surface_typeContext>(0);
}

WavefrontOBJParser::DegreeContext* WavefrontOBJParser::StatementContext::degree() {
  return getRuleContext<WavefrontOBJParser::DegreeContext>(0);
}

WavefrontOBJParser::Basis_matrixContext* WavefrontOBJParser::StatementContext::basis_matrix() {
  return getRuleContext<WavefrontOBJParser::Basis_matrixContext>(0);
}

WavefrontOBJParser::StepContext* WavefrontOBJParser::StatementContext::step() {
  return getRuleContext<WavefrontOBJParser::StepContext>(0);
}

WavefrontOBJParser::Free_form_surfaceContext* WavefrontOBJParser::StatementContext::free_form_surface() {
  return getRuleContext<WavefrontOBJParser::Free_form_surfaceContext>(0);
}

WavefrontOBJParser::ConnectivityContext* WavefrontOBJParser::StatementContext::connectivity() {
  return getRuleContext<WavefrontOBJParser::ConnectivityContext>(0);
}

WavefrontOBJParser::GroupContext* WavefrontOBJParser::StatementContext::group() {
  return getRuleContext<WavefrontOBJParser::GroupContext>(0);
}

WavefrontOBJParser::Smoothing_groupContext* WavefrontOBJParser::StatementContext::smoothing_group() {
  return getRuleContext<WavefrontOBJParser::Smoothing_groupContext>(0);
}

WavefrontOBJParser::Merging_groupContext* WavefrontOBJParser::StatementContext::merging_group() {
  return getRuleContext<WavefrontOBJParser::Merging_groupContext>(0);
}

WavefrontOBJParser::Object_nameContext* WavefrontOBJParser::StatementContext::object_name() {
  return getRuleContext<WavefrontOBJParser::Object_nameContext>(0);
}

WavefrontOBJParser::BevelContext* WavefrontOBJParser::StatementContext::bevel() {
  return getRuleContext<WavefrontOBJParser::BevelContext>(0);
}

WavefrontOBJParser::Color_interpolationContext* WavefrontOBJParser::StatementContext::color_interpolation() {
  return getRuleContext<WavefrontOBJParser::Color_interpolationContext>(0);
}

WavefrontOBJParser::Dissolve_interpolationContext* WavefrontOBJParser::StatementContext::dissolve_interpolation() {
  return getRuleContext<WavefrontOBJParser::Dissolve_interpolationContext>(0);
}

WavefrontOBJParser::Level_of_detailContext* WavefrontOBJParser::StatementContext::level_of_detail() {
  return getRuleContext<WavefrontOBJParser::Level_of_detailContext>(0);
}

WavefrontOBJParser::Map_libraryContext* WavefrontOBJParser::StatementContext::map_library() {
  return getRuleContext<WavefrontOBJParser::Map_libraryContext>(0);
}

WavefrontOBJParser::Use_mapContext* WavefrontOBJParser::StatementContext::use_map() {
  return getRuleContext<WavefrontOBJParser::Use_mapContext>(0);
}

WavefrontOBJParser::Material_libraryContext* WavefrontOBJParser::StatementContext::material_library() {
  return getRuleContext<WavefrontOBJParser::Material_libraryContext>(0);
}

WavefrontOBJParser::Use_materialContext* WavefrontOBJParser::StatementContext::use_material() {
  return getRuleContext<WavefrontOBJParser::Use_materialContext>(0);
}

WavefrontOBJParser::Shadow_objectContext* WavefrontOBJParser::StatementContext::shadow_object() {
  return getRuleContext<WavefrontOBJParser::Shadow_objectContext>(0);
}

WavefrontOBJParser::Trace_objectContext* WavefrontOBJParser::StatementContext::trace_object() {
  return getRuleContext<WavefrontOBJParser::Trace_objectContext>(0);
}

WavefrontOBJParser::Curve_approximation_techniqueContext* WavefrontOBJParser::StatementContext::curve_approximation_technique() {
  return getRuleContext<WavefrontOBJParser::Curve_approximation_techniqueContext>(0);
}

WavefrontOBJParser::Surface_approximation_techniqueContext* WavefrontOBJParser::StatementContext::surface_approximation_technique() {
  return getRuleContext<WavefrontOBJParser::Surface_approximation_techniqueContext>(0);
}


size_t WavefrontOBJParser::StatementContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleStatement;
}

void WavefrontOBJParser::StatementContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterStatement(this);
}

void WavefrontOBJParser::StatementContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitStatement(this);
}

WavefrontOBJParser::StatementContext* WavefrontOBJParser::statement() {
  StatementContext *_localctx = _tracker.createInstance<StatementContext>(_ctx, getState());
  enterRule(_localctx, 2, WavefrontOBJParser::RuleStatement);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    setState(136);
    _errHandler->sync(this);
    switch (getInterpreter<atn::ParserATNSimulator>()->adaptivePredict(_input, 4, _ctx)) {
    case 1: {
      enterOuterAlt(_localctx, 1);
      setState(105);
      call();
      break;
    }

    case 2: {
      enterOuterAlt(_localctx, 2);
      setState(106);
      csh();
      break;
    }

    case 3: {
      enterOuterAlt(_localctx, 3);
      setState(107);
      vertex();
      break;
    }

    case 4: {
      enterOuterAlt(_localctx, 4);
      setState(108);
      vertex_normal();
      break;
    }

    case 5: {
      enterOuterAlt(_localctx, 5);
      setState(109);
      vertex_texture();
      break;
    }

    case 6: {
      enterOuterAlt(_localctx, 6);
      setState(110);
      vertex_parameter();
      break;
    }

    case 7: {
      enterOuterAlt(_localctx, 7);
      setState(111);
      points();
      break;
    }

    case 8: {
      enterOuterAlt(_localctx, 8);
      setState(112);
      lines();
      break;
    }

    case 9: {
      enterOuterAlt(_localctx, 9);
      setState(113);
      faces();
      break;
    }

    case 10: {
      enterOuterAlt(_localctx, 10);
      setState(114);
      curve_surface_type();
      break;
    }

    case 11: {
      enterOuterAlt(_localctx, 11);
      setState(115);
      degree();
      break;
    }

    case 12: {
      enterOuterAlt(_localctx, 12);
      setState(116);
      basis_matrix();
      break;
    }

    case 13: {
      enterOuterAlt(_localctx, 13);
      setState(117);
      step();
      break;
    }

    case 14: {
      enterOuterAlt(_localctx, 14);
      setState(118);
      free_form_surface();
      break;
    }

    case 15: {
      enterOuterAlt(_localctx, 15);
      setState(119);
      connectivity();
      break;
    }

    case 16: {
      enterOuterAlt(_localctx, 16);
      setState(120);
      group();
      break;
    }

    case 17: {
      enterOuterAlt(_localctx, 17);
      setState(121);
      smoothing_group();
      break;
    }

    case 18: {
      enterOuterAlt(_localctx, 18);
      setState(122);
      merging_group();
      break;
    }

    case 19: {
      enterOuterAlt(_localctx, 19);
      setState(123);
      object_name();
      break;
    }

    case 20: {
      enterOuterAlt(_localctx, 20);
      setState(124);
      bevel();
      break;
    }

    case 21: {
      enterOuterAlt(_localctx, 21);
      setState(125);
      color_interpolation();
      break;
    }

    case 22: {
      enterOuterAlt(_localctx, 22);
      setState(126);
      dissolve_interpolation();
      break;
    }

    case 23: {
      enterOuterAlt(_localctx, 23);
      setState(127);
      level_of_detail();
      break;
    }

    case 24: {
      enterOuterAlt(_localctx, 24);
      setState(128);
      map_library();
      break;
    }

    case 25: {
      enterOuterAlt(_localctx, 25);
      setState(129);
      use_map();
      break;
    }

    case 26: {
      enterOuterAlt(_localctx, 26);
      setState(130);
      material_library();
      break;
    }

    case 27: {
      enterOuterAlt(_localctx, 27);
      setState(131);
      use_material();
      break;
    }

    case 28: {
      enterOuterAlt(_localctx, 28);
      setState(132);
      shadow_object();
      break;
    }

    case 29: {
      enterOuterAlt(_localctx, 29);
      setState(133);
      trace_object();
      break;
    }

    case 30: {
      enterOuterAlt(_localctx, 30);
      setState(134);
      curve_approximation_technique();
      break;
    }

    case 31: {
      enterOuterAlt(_localctx, 31);
      setState(135);
      surface_approximation_technique();
      break;
    }

    default:
      break;
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- CallContext ------------------------------------------------------------------

WavefrontOBJParser::CallContext::CallContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::CallContext::FILENAME() {
  return getToken(WavefrontOBJParser::FILENAME, 0);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::CallContext::NON_WS() {
  return getTokens(WavefrontOBJParser::NON_WS);
}

tree::TerminalNode* WavefrontOBJParser::CallContext::NON_WS(size_t i) {
  return getToken(WavefrontOBJParser::NON_WS, i);
}


size_t WavefrontOBJParser::CallContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleCall;
}

void WavefrontOBJParser::CallContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterCall(this);
}

void WavefrontOBJParser::CallContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitCall(this);
}

WavefrontOBJParser::CallContext* WavefrontOBJParser::call() {
  CallContext *_localctx = _tracker.createInstance<CallContext>(_ctx, getState());
  enterRule(_localctx, 4, WavefrontOBJParser::RuleCall);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(138);
    antlrcpp::downCast<CallContext *>(_localctx)->filename = match(WavefrontOBJParser::FILENAME);
    setState(142);
    _errHandler->sync(this);
    _la = _input->LA(1);
    while (_la == WavefrontOBJParser::NON_WS) {
      setState(139);
      antlrcpp::downCast<CallContext *>(_localctx)->args = match(WavefrontOBJParser::NON_WS);
      setState(144);
      _errHandler->sync(this);
      _la = _input->LA(1);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- CshContext ------------------------------------------------------------------

WavefrontOBJParser::CshContext::CshContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::CshContext::FILENAME() {
  return getToken(WavefrontOBJParser::FILENAME, 0);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::CshContext::NON_WS() {
  return getTokens(WavefrontOBJParser::NON_WS);
}

tree::TerminalNode* WavefrontOBJParser::CshContext::NON_WS(size_t i) {
  return getToken(WavefrontOBJParser::NON_WS, i);
}


size_t WavefrontOBJParser::CshContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleCsh;
}

void WavefrontOBJParser::CshContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterCsh(this);
}

void WavefrontOBJParser::CshContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitCsh(this);
}

WavefrontOBJParser::CshContext* WavefrontOBJParser::csh() {
  CshContext *_localctx = _tracker.createInstance<CshContext>(_ctx, getState());
  enterRule(_localctx, 6, WavefrontOBJParser::RuleCsh);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(145);
    antlrcpp::downCast<CshContext *>(_localctx)->command = match(WavefrontOBJParser::FILENAME);
    setState(147);
    _errHandler->sync(this);

    _la = _input->LA(1);
    if (_la == WavefrontOBJParser::T__0) {
      setState(146);
      match(WavefrontOBJParser::T__0);
    }
    setState(152);
    _errHandler->sync(this);
    _la = _input->LA(1);
    while (_la == WavefrontOBJParser::NON_WS) {
      setState(149);
      antlrcpp::downCast<CshContext *>(_localctx)->args = match(WavefrontOBJParser::NON_WS);
      setState(154);
      _errHandler->sync(this);
      _la = _input->LA(1);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- VertexContext ------------------------------------------------------------------

WavefrontOBJParser::VertexContext::VertexContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::VertexContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::VertexContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::VertexContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleVertex;
}

void WavefrontOBJParser::VertexContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterVertex(this);
}

void WavefrontOBJParser::VertexContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitVertex(this);
}

WavefrontOBJParser::VertexContext* WavefrontOBJParser::vertex() {
  VertexContext *_localctx = _tracker.createInstance<VertexContext>(_ctx, getState());
  enterRule(_localctx, 8, WavefrontOBJParser::RuleVertex);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(155);
    match(WavefrontOBJParser::T__1);
    setState(156);
    antlrcpp::downCast<VertexContext *>(_localctx)->x = decimal();
    setState(157);
    antlrcpp::downCast<VertexContext *>(_localctx)->y = decimal();
    setState(158);
    antlrcpp::downCast<VertexContext *>(_localctx)->z = decimal();
    setState(160);
    _errHandler->sync(this);

    _la = _input->LA(1);
    if (_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL) {
      setState(159);
      antlrcpp::downCast<VertexContext *>(_localctx)->w = decimal();
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Vertex_parameterContext ------------------------------------------------------------------

WavefrontOBJParser::Vertex_parameterContext::Vertex_parameterContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Vertex_parameterContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Vertex_parameterContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::Vertex_parameterContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleVertex_parameter;
}

void WavefrontOBJParser::Vertex_parameterContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterVertex_parameter(this);
}

void WavefrontOBJParser::Vertex_parameterContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitVertex_parameter(this);
}

WavefrontOBJParser::Vertex_parameterContext* WavefrontOBJParser::vertex_parameter() {
  Vertex_parameterContext *_localctx = _tracker.createInstance<Vertex_parameterContext>(_ctx, getState());
  enterRule(_localctx, 10, WavefrontOBJParser::RuleVertex_parameter);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(162);
    match(WavefrontOBJParser::T__2);
    setState(163);
    antlrcpp::downCast<Vertex_parameterContext *>(_localctx)->u = decimal();
    setState(165);
    _errHandler->sync(this);

    switch (getInterpreter<atn::ParserATNSimulator>()->adaptivePredict(_input, 9, _ctx)) {
    case 1: {
      setState(164);
      antlrcpp::downCast<Vertex_parameterContext *>(_localctx)->v = decimal();
      break;
    }

    default:
      break;
    }
    setState(168);
    _errHandler->sync(this);

    _la = _input->LA(1);
    if (_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL) {
      setState(167);
      antlrcpp::downCast<Vertex_parameterContext *>(_localctx)->w = decimal();
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Vertex_normalContext ------------------------------------------------------------------

WavefrontOBJParser::Vertex_normalContext::Vertex_normalContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Vertex_normalContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Vertex_normalContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::Vertex_normalContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleVertex_normal;
}

void WavefrontOBJParser::Vertex_normalContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterVertex_normal(this);
}

void WavefrontOBJParser::Vertex_normalContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitVertex_normal(this);
}

WavefrontOBJParser::Vertex_normalContext* WavefrontOBJParser::vertex_normal() {
  Vertex_normalContext *_localctx = _tracker.createInstance<Vertex_normalContext>(_ctx, getState());
  enterRule(_localctx, 12, WavefrontOBJParser::RuleVertex_normal);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(170);
    match(WavefrontOBJParser::T__3);
    setState(171);
    antlrcpp::downCast<Vertex_normalContext *>(_localctx)->x = decimal();
    setState(172);
    antlrcpp::downCast<Vertex_normalContext *>(_localctx)->y = decimal();
    setState(173);
    antlrcpp::downCast<Vertex_normalContext *>(_localctx)->z = decimal();
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Vertex_textureContext ------------------------------------------------------------------

WavefrontOBJParser::Vertex_textureContext::Vertex_textureContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Vertex_textureContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Vertex_textureContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::Vertex_textureContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleVertex_texture;
}

void WavefrontOBJParser::Vertex_textureContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterVertex_texture(this);
}

void WavefrontOBJParser::Vertex_textureContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitVertex_texture(this);
}

WavefrontOBJParser::Vertex_textureContext* WavefrontOBJParser::vertex_texture() {
  Vertex_textureContext *_localctx = _tracker.createInstance<Vertex_textureContext>(_ctx, getState());
  enterRule(_localctx, 14, WavefrontOBJParser::RuleVertex_texture);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(175);
    match(WavefrontOBJParser::T__4);
    setState(176);
    antlrcpp::downCast<Vertex_textureContext *>(_localctx)->u = decimal();
    setState(178);
    _errHandler->sync(this);

    switch (getInterpreter<atn::ParserATNSimulator>()->adaptivePredict(_input, 11, _ctx)) {
    case 1: {
      setState(177);
      antlrcpp::downCast<Vertex_textureContext *>(_localctx)->v = decimal();
      break;
    }

    default:
      break;
    }
    setState(181);
    _errHandler->sync(this);

    _la = _input->LA(1);
    if (_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL) {
      setState(180);
      antlrcpp::downCast<Vertex_textureContext *>(_localctx)->w = decimal();
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Curve_surface_typeContext ------------------------------------------------------------------

WavefrontOBJParser::Curve_surface_typeContext::Curve_surface_typeContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}


size_t WavefrontOBJParser::Curve_surface_typeContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleCurve_surface_type;
}

void WavefrontOBJParser::Curve_surface_typeContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterCurve_surface_type(this);
}

void WavefrontOBJParser::Curve_surface_typeContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitCurve_surface_type(this);
}

WavefrontOBJParser::Curve_surface_typeContext* WavefrontOBJParser::curve_surface_type() {
  Curve_surface_typeContext *_localctx = _tracker.createInstance<Curve_surface_typeContext>(_ctx, getState());
  enterRule(_localctx, 16, WavefrontOBJParser::RuleCurve_surface_type);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(183);
    match(WavefrontOBJParser::T__5);
    setState(185);
    _errHandler->sync(this);

    _la = _input->LA(1);
    if (_la == WavefrontOBJParser::T__6) {
      setState(184);
      antlrcpp::downCast<Curve_surface_typeContext *>(_localctx)->rational = match(WavefrontOBJParser::T__6);
    }
    setState(187);
    antlrcpp::downCast<Curve_surface_typeContext *>(_localctx)->cstype = _input->LT(1);
    _la = _input->LA(1);
    if (!((((_la & ~ 0x3fULL) == 0) &&
      ((1ULL << _la) & 7936) != 0))) {
      antlrcpp::downCast<Curve_surface_typeContext *>(_localctx)->cstype = _errHandler->recoverInline(this);
    }
    else {
      _errHandler->reportMatch(this);
      consume();
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- DegreeContext ------------------------------------------------------------------

WavefrontOBJParser::DegreeContext::DegreeContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::DegreeContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::DegreeContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::DegreeContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleDegree;
}

void WavefrontOBJParser::DegreeContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterDegree(this);
}

void WavefrontOBJParser::DegreeContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitDegree(this);
}

WavefrontOBJParser::DegreeContext* WavefrontOBJParser::degree() {
  DegreeContext *_localctx = _tracker.createInstance<DegreeContext>(_ctx, getState());
  enterRule(_localctx, 18, WavefrontOBJParser::RuleDegree);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(189);
    match(WavefrontOBJParser::T__12);
    setState(190);
    antlrcpp::downCast<DegreeContext *>(_localctx)->u = match(WavefrontOBJParser::INTEGER);
    setState(192);
    _errHandler->sync(this);

    _la = _input->LA(1);
    if (_la == WavefrontOBJParser::INTEGER) {
      setState(191);
      antlrcpp::downCast<DegreeContext *>(_localctx)->v = match(WavefrontOBJParser::INTEGER);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Basis_matrixContext ------------------------------------------------------------------

WavefrontOBJParser::Basis_matrixContext::Basis_matrixContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Basis_matrixContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Basis_matrixContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::Basis_matrixContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleBasis_matrix;
}

void WavefrontOBJParser::Basis_matrixContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterBasis_matrix(this);
}

void WavefrontOBJParser::Basis_matrixContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitBasis_matrix(this);
}

WavefrontOBJParser::Basis_matrixContext* WavefrontOBJParser::basis_matrix() {
  Basis_matrixContext *_localctx = _tracker.createInstance<Basis_matrixContext>(_ctx, getState());
  enterRule(_localctx, 20, WavefrontOBJParser::RuleBasis_matrix);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(194);
    match(WavefrontOBJParser::T__13);
    setState(195);
    _la = _input->LA(1);
    if (!(_la == WavefrontOBJParser::T__1

    || _la == WavefrontOBJParser::T__14)) {
    _errHandler->recoverInline(this);
    }
    else {
      _errHandler->reportMatch(this);
      consume();
    }
    setState(197); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(196);
      decimal();
      setState(199); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- StepContext ------------------------------------------------------------------

WavefrontOBJParser::StepContext::StepContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::StepContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::StepContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::StepContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleStep;
}

void WavefrontOBJParser::StepContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterStep(this);
}

void WavefrontOBJParser::StepContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitStep(this);
}

WavefrontOBJParser::StepContext* WavefrontOBJParser::step() {
  StepContext *_localctx = _tracker.createInstance<StepContext>(_ctx, getState());
  enterRule(_localctx, 22, WavefrontOBJParser::RuleStep);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(201);
    match(WavefrontOBJParser::T__15);
    setState(202);
    antlrcpp::downCast<StepContext *>(_localctx)->u = match(WavefrontOBJParser::INTEGER);
    setState(204);
    _errHandler->sync(this);

    _la = _input->LA(1);
    if (_la == WavefrontOBJParser::INTEGER) {
      setState(203);
      antlrcpp::downCast<StepContext *>(_localctx)->v = match(WavefrontOBJParser::INTEGER);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- PointsContext ------------------------------------------------------------------

WavefrontOBJParser::PointsContext::PointsContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::PointsContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::PointsContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::PointsContext::getRuleIndex() const {
  return WavefrontOBJParser::RulePoints;
}

void WavefrontOBJParser::PointsContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterPoints(this);
}

void WavefrontOBJParser::PointsContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitPoints(this);
}

WavefrontOBJParser::PointsContext* WavefrontOBJParser::points() {
  PointsContext *_localctx = _tracker.createInstance<PointsContext>(_ctx, getState());
  enterRule(_localctx, 24, WavefrontOBJParser::RulePoints);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(206);
    match(WavefrontOBJParser::T__16);
    setState(208); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(207);
      antlrcpp::downCast<PointsContext *>(_localctx)->v = match(WavefrontOBJParser::INTEGER);
      setState(210); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- LinesContext ------------------------------------------------------------------

WavefrontOBJParser::LinesContext::LinesContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::LinesContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::LinesContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::LinesContext::INTEGER_PAIR() {
  return getTokens(WavefrontOBJParser::INTEGER_PAIR);
}

tree::TerminalNode* WavefrontOBJParser::LinesContext::INTEGER_PAIR(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER_PAIR, i);
}


size_t WavefrontOBJParser::LinesContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleLines;
}

void WavefrontOBJParser::LinesContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterLines(this);
}

void WavefrontOBJParser::LinesContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitLines(this);
}

WavefrontOBJParser::LinesContext* WavefrontOBJParser::lines() {
  LinesContext *_localctx = _tracker.createInstance<LinesContext>(_ctx, getState());
  enterRule(_localctx, 26, WavefrontOBJParser::RuleLines);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(212);
    match(WavefrontOBJParser::T__17);
    setState(214); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(213);
      antlrcpp::downCast<LinesContext *>(_localctx)->v = _input->LT(1);
      _la = _input->LA(1);
      if (!(_la == WavefrontOBJParser::INTEGER_PAIR

      || _la == WavefrontOBJParser::INTEGER)) {
        antlrcpp::downCast<LinesContext *>(_localctx)->v = _errHandler->recoverInline(this);
      }
      else {
        _errHandler->reportMatch(this);
        consume();
      }
      setState(216); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER_PAIR

    || _la == WavefrontOBJParser::INTEGER);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- FacesContext ------------------------------------------------------------------

WavefrontOBJParser::FacesContext::FacesContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::FacesContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::FacesContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::FacesContext::INTEGER_PAIR() {
  return getTokens(WavefrontOBJParser::INTEGER_PAIR);
}

tree::TerminalNode* WavefrontOBJParser::FacesContext::INTEGER_PAIR(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER_PAIR, i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::FacesContext::INTEGER_TRIPLET() {
  return getTokens(WavefrontOBJParser::INTEGER_TRIPLET);
}

tree::TerminalNode* WavefrontOBJParser::FacesContext::INTEGER_TRIPLET(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER_TRIPLET, i);
}


size_t WavefrontOBJParser::FacesContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleFaces;
}

void WavefrontOBJParser::FacesContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterFaces(this);
}

void WavefrontOBJParser::FacesContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitFaces(this);
}

WavefrontOBJParser::FacesContext* WavefrontOBJParser::faces() {
  FacesContext *_localctx = _tracker.createInstance<FacesContext>(_ctx, getState());
  enterRule(_localctx, 28, WavefrontOBJParser::RuleFaces);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(218);
    match(WavefrontOBJParser::T__18);
    setState(220); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(219);
      antlrcpp::downCast<FacesContext *>(_localctx)->v = _input->LT(1);
      _la = _input->LA(1);
      if (!((((_la & ~ 0x3fULL) == 0) &&
        ((1ULL << _la) & 31525197391593472) != 0))) {
        antlrcpp::downCast<FacesContext *>(_localctx)->v = _errHandler->recoverInline(this);
      }
      else {
        _errHandler->reportMatch(this);
        consume();
      }
      setState(222); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while ((((_la & ~ 0x3fULL) == 0) &&
      ((1ULL << _la) & 31525197391593472) != 0));
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Free_form_surfaceContext ------------------------------------------------------------------

WavefrontOBJParser::Free_form_surfaceContext::Free_form_surfaceContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

WavefrontOBJParser::EndContext* WavefrontOBJParser::Free_form_surfaceContext::end() {
  return getRuleContext<WavefrontOBJParser::EndContext>(0);
}

WavefrontOBJParser::CurveContext* WavefrontOBJParser::Free_form_surfaceContext::curve() {
  return getRuleContext<WavefrontOBJParser::CurveContext>(0);
}

WavefrontOBJParser::Curve2dContext* WavefrontOBJParser::Free_form_surfaceContext::curve2d() {
  return getRuleContext<WavefrontOBJParser::Curve2dContext>(0);
}

WavefrontOBJParser::SurfaceContext* WavefrontOBJParser::Free_form_surfaceContext::surface() {
  return getRuleContext<WavefrontOBJParser::SurfaceContext>(0);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Free_form_surfaceContext::NL() {
  return getTokens(WavefrontOBJParser::NL);
}

tree::TerminalNode* WavefrontOBJParser::Free_form_surfaceContext::NL(size_t i) {
  return getToken(WavefrontOBJParser::NL, i);
}

std::vector<WavefrontOBJParser::ParameterContext *> WavefrontOBJParser::Free_form_surfaceContext::parameter() {
  return getRuleContexts<WavefrontOBJParser::ParameterContext>();
}

WavefrontOBJParser::ParameterContext* WavefrontOBJParser::Free_form_surfaceContext::parameter(size_t i) {
  return getRuleContext<WavefrontOBJParser::ParameterContext>(i);
}

std::vector<WavefrontOBJParser::Outer_trimming_loopContext *> WavefrontOBJParser::Free_form_surfaceContext::outer_trimming_loop() {
  return getRuleContexts<WavefrontOBJParser::Outer_trimming_loopContext>();
}

WavefrontOBJParser::Outer_trimming_loopContext* WavefrontOBJParser::Free_form_surfaceContext::outer_trimming_loop(size_t i) {
  return getRuleContext<WavefrontOBJParser::Outer_trimming_loopContext>(i);
}

std::vector<WavefrontOBJParser::Inner_trimming_loopContext *> WavefrontOBJParser::Free_form_surfaceContext::inner_trimming_loop() {
  return getRuleContexts<WavefrontOBJParser::Inner_trimming_loopContext>();
}

WavefrontOBJParser::Inner_trimming_loopContext* WavefrontOBJParser::Free_form_surfaceContext::inner_trimming_loop(size_t i) {
  return getRuleContext<WavefrontOBJParser::Inner_trimming_loopContext>(i);
}

std::vector<WavefrontOBJParser::Special_curveContext *> WavefrontOBJParser::Free_form_surfaceContext::special_curve() {
  return getRuleContexts<WavefrontOBJParser::Special_curveContext>();
}

WavefrontOBJParser::Special_curveContext* WavefrontOBJParser::Free_form_surfaceContext::special_curve(size_t i) {
  return getRuleContext<WavefrontOBJParser::Special_curveContext>(i);
}

std::vector<WavefrontOBJParser::Special_pointContext *> WavefrontOBJParser::Free_form_surfaceContext::special_point() {
  return getRuleContexts<WavefrontOBJParser::Special_pointContext>();
}

WavefrontOBJParser::Special_pointContext* WavefrontOBJParser::Free_form_surfaceContext::special_point(size_t i) {
  return getRuleContext<WavefrontOBJParser::Special_pointContext>(i);
}


size_t WavefrontOBJParser::Free_form_surfaceContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleFree_form_surface;
}

void WavefrontOBJParser::Free_form_surfaceContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterFree_form_surface(this);
}

void WavefrontOBJParser::Free_form_surfaceContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitFree_form_surface(this);
}

WavefrontOBJParser::Free_form_surfaceContext* WavefrontOBJParser::free_form_surface() {
  Free_form_surfaceContext *_localctx = _tracker.createInstance<Free_form_surfaceContext>(_ctx, getState());
  enterRule(_localctx, 30, WavefrontOBJParser::RuleFree_form_surface);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(227);
    _errHandler->sync(this);
    switch (_input->LA(1)) {
      case WavefrontOBJParser::T__19: {
        setState(224);
        curve();
        break;
      }

      case WavefrontOBJParser::T__20: {
        setState(225);
        curve2d();
        break;
      }

      case WavefrontOBJParser::T__21: {
        setState(226);
        surface();
        break;
      }

    default:
      throw NoViableAltException(this);
    }
    setState(230); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(229);
      match(WavefrontOBJParser::NL);
      setState(232); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::NL);
    setState(248);
    _errHandler->sync(this);
    _la = _input->LA(1);
    while ((((_la & ~ 0x3fULL) == 0) &&
      ((1ULL << _la) & 260046848) != 0)) {
      setState(239);
      _errHandler->sync(this);
      switch (_input->LA(1)) {
        case WavefrontOBJParser::T__22: {
          setState(234);
          parameter();
          break;
        }

        case WavefrontOBJParser::T__23: {
          setState(235);
          outer_trimming_loop();
          break;
        }

        case WavefrontOBJParser::T__24: {
          setState(236);
          inner_trimming_loop();
          break;
        }

        case WavefrontOBJParser::T__25: {
          setState(237);
          special_curve();
          break;
        }

        case WavefrontOBJParser::T__26: {
          setState(238);
          special_point();
          break;
        }

      default:
        throw NoViableAltException(this);
      }
      setState(242); 
      _errHandler->sync(this);
      _la = _input->LA(1);
      do {
        setState(241);
        match(WavefrontOBJParser::NL);
        setState(244); 
        _errHandler->sync(this);
        _la = _input->LA(1);
      } while (_la == WavefrontOBJParser::NL);
      setState(250);
      _errHandler->sync(this);
      _la = _input->LA(1);
    }
    setState(251);
    end();
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- CurveContext ------------------------------------------------------------------

WavefrontOBJParser::CurveContext::CurveContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::CurveContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::CurveContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::CurveContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::CurveContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::CurveContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleCurve;
}

void WavefrontOBJParser::CurveContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterCurve(this);
}

void WavefrontOBJParser::CurveContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitCurve(this);
}

WavefrontOBJParser::CurveContext* WavefrontOBJParser::curve() {
  CurveContext *_localctx = _tracker.createInstance<CurveContext>(_ctx, getState());
  enterRule(_localctx, 32, WavefrontOBJParser::RuleCurve);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(253);
    match(WavefrontOBJParser::T__19);
    setState(254);
    antlrcpp::downCast<CurveContext *>(_localctx)->u0 = decimal();
    setState(255);
    antlrcpp::downCast<CurveContext *>(_localctx)->u1 = decimal();
    setState(257); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(256);
      antlrcpp::downCast<CurveContext *>(_localctx)->v = match(WavefrontOBJParser::INTEGER);
      setState(259); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Curve2dContext ------------------------------------------------------------------

WavefrontOBJParser::Curve2dContext::Curve2dContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Curve2dContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::Curve2dContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::Curve2dContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleCurve2d;
}

void WavefrontOBJParser::Curve2dContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterCurve2d(this);
}

void WavefrontOBJParser::Curve2dContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitCurve2d(this);
}

WavefrontOBJParser::Curve2dContext* WavefrontOBJParser::curve2d() {
  Curve2dContext *_localctx = _tracker.createInstance<Curve2dContext>(_ctx, getState());
  enterRule(_localctx, 34, WavefrontOBJParser::RuleCurve2d);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(261);
    match(WavefrontOBJParser::T__20);
    setState(262);
    antlrcpp::downCast<Curve2dContext *>(_localctx)->vp1 = match(WavefrontOBJParser::INTEGER);
    setState(264); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(263);
      antlrcpp::downCast<Curve2dContext *>(_localctx)->vp2 = match(WavefrontOBJParser::INTEGER);
      setState(266); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- SurfaceContext ------------------------------------------------------------------

WavefrontOBJParser::SurfaceContext::SurfaceContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::SurfaceContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::SurfaceContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::SurfaceContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::SurfaceContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::SurfaceContext::INTEGER_PAIR() {
  return getTokens(WavefrontOBJParser::INTEGER_PAIR);
}

tree::TerminalNode* WavefrontOBJParser::SurfaceContext::INTEGER_PAIR(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER_PAIR, i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::SurfaceContext::INTEGER_TRIPLET() {
  return getTokens(WavefrontOBJParser::INTEGER_TRIPLET);
}

tree::TerminalNode* WavefrontOBJParser::SurfaceContext::INTEGER_TRIPLET(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER_TRIPLET, i);
}


size_t WavefrontOBJParser::SurfaceContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleSurface;
}

void WavefrontOBJParser::SurfaceContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterSurface(this);
}

void WavefrontOBJParser::SurfaceContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitSurface(this);
}

WavefrontOBJParser::SurfaceContext* WavefrontOBJParser::surface() {
  SurfaceContext *_localctx = _tracker.createInstance<SurfaceContext>(_ctx, getState());
  enterRule(_localctx, 36, WavefrontOBJParser::RuleSurface);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(268);
    match(WavefrontOBJParser::T__21);
    setState(269);
    antlrcpp::downCast<SurfaceContext *>(_localctx)->s0 = decimal();
    setState(270);
    antlrcpp::downCast<SurfaceContext *>(_localctx)->s1 = decimal();
    setState(271);
    antlrcpp::downCast<SurfaceContext *>(_localctx)->t0 = decimal();
    setState(272);
    antlrcpp::downCast<SurfaceContext *>(_localctx)->t1 = decimal();
    setState(274); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(273);
      antlrcpp::downCast<SurfaceContext *>(_localctx)->v = _input->LT(1);
      _la = _input->LA(1);
      if (!((((_la & ~ 0x3fULL) == 0) &&
        ((1ULL << _la) & 31525197391593472) != 0))) {
        antlrcpp::downCast<SurfaceContext *>(_localctx)->v = _errHandler->recoverInline(this);
      }
      else {
        _errHandler->reportMatch(this);
        consume();
      }
      setState(276); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while ((((_la & ~ 0x3fULL) == 0) &&
      ((1ULL << _la) & 31525197391593472) != 0));
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- ParameterContext ------------------------------------------------------------------

WavefrontOBJParser::ParameterContext::ParameterContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::ParameterContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::ParameterContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::ParameterContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleParameter;
}

void WavefrontOBJParser::ParameterContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterParameter(this);
}

void WavefrontOBJParser::ParameterContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitParameter(this);
}

WavefrontOBJParser::ParameterContext* WavefrontOBJParser::parameter() {
  ParameterContext *_localctx = _tracker.createInstance<ParameterContext>(_ctx, getState());
  enterRule(_localctx, 38, WavefrontOBJParser::RuleParameter);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(278);
    match(WavefrontOBJParser::T__22);
    setState(279);
    _la = _input->LA(1);
    if (!(_la == WavefrontOBJParser::T__1

    || _la == WavefrontOBJParser::T__14)) {
    _errHandler->recoverInline(this);
    }
    else {
      _errHandler->reportMatch(this);
      consume();
    }
    setState(281); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(280);
      antlrcpp::downCast<ParameterContext *>(_localctx)->p = decimal();
      setState(283); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Outer_trimming_loopContext ------------------------------------------------------------------

WavefrontOBJParser::Outer_trimming_loopContext::Outer_trimming_loopContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Outer_trimming_loopContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Outer_trimming_loopContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Outer_trimming_loopContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::Outer_trimming_loopContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::Outer_trimming_loopContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleOuter_trimming_loop;
}

void WavefrontOBJParser::Outer_trimming_loopContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterOuter_trimming_loop(this);
}

void WavefrontOBJParser::Outer_trimming_loopContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitOuter_trimming_loop(this);
}

WavefrontOBJParser::Outer_trimming_loopContext* WavefrontOBJParser::outer_trimming_loop() {
  Outer_trimming_loopContext *_localctx = _tracker.createInstance<Outer_trimming_loopContext>(_ctx, getState());
  enterRule(_localctx, 40, WavefrontOBJParser::RuleOuter_trimming_loop);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(285);
    match(WavefrontOBJParser::T__23);
    setState(290); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(286);
      antlrcpp::downCast<Outer_trimming_loopContext *>(_localctx)->u0 = decimal();
      setState(287);
      antlrcpp::downCast<Outer_trimming_loopContext *>(_localctx)->u1 = decimal();
      setState(288);
      antlrcpp::downCast<Outer_trimming_loopContext *>(_localctx)->curv2d = match(WavefrontOBJParser::INTEGER);
      setState(292); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Inner_trimming_loopContext ------------------------------------------------------------------

WavefrontOBJParser::Inner_trimming_loopContext::Inner_trimming_loopContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Inner_trimming_loopContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Inner_trimming_loopContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Inner_trimming_loopContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::Inner_trimming_loopContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::Inner_trimming_loopContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleInner_trimming_loop;
}

void WavefrontOBJParser::Inner_trimming_loopContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterInner_trimming_loop(this);
}

void WavefrontOBJParser::Inner_trimming_loopContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitInner_trimming_loop(this);
}

WavefrontOBJParser::Inner_trimming_loopContext* WavefrontOBJParser::inner_trimming_loop() {
  Inner_trimming_loopContext *_localctx = _tracker.createInstance<Inner_trimming_loopContext>(_ctx, getState());
  enterRule(_localctx, 42, WavefrontOBJParser::RuleInner_trimming_loop);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(294);
    match(WavefrontOBJParser::T__24);
    setState(299); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(295);
      antlrcpp::downCast<Inner_trimming_loopContext *>(_localctx)->u0 = decimal();
      setState(296);
      antlrcpp::downCast<Inner_trimming_loopContext *>(_localctx)->u1 = decimal();
      setState(297);
      antlrcpp::downCast<Inner_trimming_loopContext *>(_localctx)->curv2d = match(WavefrontOBJParser::INTEGER);
      setState(301); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Special_curveContext ------------------------------------------------------------------

WavefrontOBJParser::Special_curveContext::Special_curveContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Special_curveContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Special_curveContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Special_curveContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::Special_curveContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::Special_curveContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleSpecial_curve;
}

void WavefrontOBJParser::Special_curveContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterSpecial_curve(this);
}

void WavefrontOBJParser::Special_curveContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitSpecial_curve(this);
}

WavefrontOBJParser::Special_curveContext* WavefrontOBJParser::special_curve() {
  Special_curveContext *_localctx = _tracker.createInstance<Special_curveContext>(_ctx, getState());
  enterRule(_localctx, 44, WavefrontOBJParser::RuleSpecial_curve);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(303);
    match(WavefrontOBJParser::T__25);
    setState(308); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(304);
      antlrcpp::downCast<Special_curveContext *>(_localctx)->u0 = decimal();
      setState(305);
      antlrcpp::downCast<Special_curveContext *>(_localctx)->u1 = decimal();
      setState(306);
      antlrcpp::downCast<Special_curveContext *>(_localctx)->curv2d = match(WavefrontOBJParser::INTEGER);
      setState(310); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Special_pointContext ------------------------------------------------------------------

WavefrontOBJParser::Special_pointContext::Special_pointContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Special_pointContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::Special_pointContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}


size_t WavefrontOBJParser::Special_pointContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleSpecial_point;
}

void WavefrontOBJParser::Special_pointContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterSpecial_point(this);
}

void WavefrontOBJParser::Special_pointContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitSpecial_point(this);
}

WavefrontOBJParser::Special_pointContext* WavefrontOBJParser::special_point() {
  Special_pointContext *_localctx = _tracker.createInstance<Special_pointContext>(_ctx, getState());
  enterRule(_localctx, 46, WavefrontOBJParser::RuleSpecial_point);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(312);
    match(WavefrontOBJParser::T__26);
    setState(314); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(313);
      antlrcpp::downCast<Special_pointContext *>(_localctx)->vp = match(WavefrontOBJParser::INTEGER);
      setState(316); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::INTEGER);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- EndContext ------------------------------------------------------------------

WavefrontOBJParser::EndContext::EndContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}


size_t WavefrontOBJParser::EndContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleEnd;
}

void WavefrontOBJParser::EndContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterEnd(this);
}

void WavefrontOBJParser::EndContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitEnd(this);
}

WavefrontOBJParser::EndContext* WavefrontOBJParser::end() {
  EndContext *_localctx = _tracker.createInstance<EndContext>(_ctx, getState());
  enterRule(_localctx, 48, WavefrontOBJParser::RuleEnd);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(318);
    match(WavefrontOBJParser::T__27);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- ConnectivityContext ------------------------------------------------------------------

WavefrontOBJParser::ConnectivityContext::ConnectivityContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::ConnectivityContext::INTEGER() {
  return getTokens(WavefrontOBJParser::INTEGER);
}

tree::TerminalNode* WavefrontOBJParser::ConnectivityContext::INTEGER(size_t i) {
  return getToken(WavefrontOBJParser::INTEGER, i);
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::ConnectivityContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::ConnectivityContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::ConnectivityContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleConnectivity;
}

void WavefrontOBJParser::ConnectivityContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterConnectivity(this);
}

void WavefrontOBJParser::ConnectivityContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitConnectivity(this);
}

WavefrontOBJParser::ConnectivityContext* WavefrontOBJParser::connectivity() {
  ConnectivityContext *_localctx = _tracker.createInstance<ConnectivityContext>(_ctx, getState());
  enterRule(_localctx, 50, WavefrontOBJParser::RuleConnectivity);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(320);
    match(WavefrontOBJParser::T__28);
    setState(321);
    antlrcpp::downCast<ConnectivityContext *>(_localctx)->surf_1 = match(WavefrontOBJParser::INTEGER);
    setState(322);
    antlrcpp::downCast<ConnectivityContext *>(_localctx)->q0_1 = decimal();
    setState(323);
    antlrcpp::downCast<ConnectivityContext *>(_localctx)->q1_1 = decimal();
    setState(324);
    antlrcpp::downCast<ConnectivityContext *>(_localctx)->curv2d_1 = match(WavefrontOBJParser::INTEGER);
    setState(325);
    antlrcpp::downCast<ConnectivityContext *>(_localctx)->surf_2 = match(WavefrontOBJParser::INTEGER);
    setState(326);
    antlrcpp::downCast<ConnectivityContext *>(_localctx)->q0_2 = decimal();
    setState(327);
    antlrcpp::downCast<ConnectivityContext *>(_localctx)->q1_2 = decimal();
    setState(328);
    antlrcpp::downCast<ConnectivityContext *>(_localctx)->curv2d_2 = match(WavefrontOBJParser::INTEGER);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- GroupContext ------------------------------------------------------------------

WavefrontOBJParser::GroupContext::GroupContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::GroupContext::NAME() {
  return getTokens(WavefrontOBJParser::NAME);
}

tree::TerminalNode* WavefrontOBJParser::GroupContext::NAME(size_t i) {
  return getToken(WavefrontOBJParser::NAME, i);
}


size_t WavefrontOBJParser::GroupContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleGroup;
}

void WavefrontOBJParser::GroupContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterGroup(this);
}

void WavefrontOBJParser::GroupContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitGroup(this);
}

WavefrontOBJParser::GroupContext* WavefrontOBJParser::group() {
  GroupContext *_localctx = _tracker.createInstance<GroupContext>(_ctx, getState());
  enterRule(_localctx, 52, WavefrontOBJParser::RuleGroup);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(330);
    match(WavefrontOBJParser::T__29);
    setState(332); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(331);
      antlrcpp::downCast<GroupContext *>(_localctx)->group_name = match(WavefrontOBJParser::NAME);
      setState(334); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::NAME);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Smoothing_groupContext ------------------------------------------------------------------

WavefrontOBJParser::Smoothing_groupContext::Smoothing_groupContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Smoothing_groupContext::INTEGER() {
  return getToken(WavefrontOBJParser::INTEGER, 0);
}


size_t WavefrontOBJParser::Smoothing_groupContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleSmoothing_group;
}

void WavefrontOBJParser::Smoothing_groupContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterSmoothing_group(this);
}

void WavefrontOBJParser::Smoothing_groupContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitSmoothing_group(this);
}

WavefrontOBJParser::Smoothing_groupContext* WavefrontOBJParser::smoothing_group() {
  Smoothing_groupContext *_localctx = _tracker.createInstance<Smoothing_groupContext>(_ctx, getState());
  enterRule(_localctx, 54, WavefrontOBJParser::RuleSmoothing_group);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(336);
    match(WavefrontOBJParser::T__30);
    setState(339);
    _errHandler->sync(this);
    switch (_input->LA(1)) {
      case WavefrontOBJParser::INTEGER: {
        setState(337);
        antlrcpp::downCast<Smoothing_groupContext *>(_localctx)->group_number = match(WavefrontOBJParser::INTEGER);
        break;
      }

      case WavefrontOBJParser::T__31: {
        setState(338);
        match(WavefrontOBJParser::T__31);
        break;
      }

    default:
      throw NoViableAltException(this);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Merging_groupContext ------------------------------------------------------------------

WavefrontOBJParser::Merging_groupContext::Merging_groupContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Merging_groupContext::INTEGER() {
  return getToken(WavefrontOBJParser::INTEGER, 0);
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Merging_groupContext::decimal() {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(0);
}


size_t WavefrontOBJParser::Merging_groupContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleMerging_group;
}

void WavefrontOBJParser::Merging_groupContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterMerging_group(this);
}

void WavefrontOBJParser::Merging_groupContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitMerging_group(this);
}

WavefrontOBJParser::Merging_groupContext* WavefrontOBJParser::merging_group() {
  Merging_groupContext *_localctx = _tracker.createInstance<Merging_groupContext>(_ctx, getState());
  enterRule(_localctx, 56, WavefrontOBJParser::RuleMerging_group);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(341);
    match(WavefrontOBJParser::T__32);
    setState(345);
    _errHandler->sync(this);
    switch (_input->LA(1)) {
      case WavefrontOBJParser::INTEGER: {
        setState(342);
        antlrcpp::downCast<Merging_groupContext *>(_localctx)->group_number = match(WavefrontOBJParser::INTEGER);
        setState(343);
        antlrcpp::downCast<Merging_groupContext *>(_localctx)->res = decimal();
        break;
      }

      case WavefrontOBJParser::T__31: {
        setState(344);
        match(WavefrontOBJParser::T__31);
        break;
      }

    default:
      throw NoViableAltException(this);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Object_nameContext ------------------------------------------------------------------

WavefrontOBJParser::Object_nameContext::Object_nameContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Object_nameContext::NAME() {
  return getToken(WavefrontOBJParser::NAME, 0);
}


size_t WavefrontOBJParser::Object_nameContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleObject_name;
}

void WavefrontOBJParser::Object_nameContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterObject_name(this);
}

void WavefrontOBJParser::Object_nameContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitObject_name(this);
}

WavefrontOBJParser::Object_nameContext* WavefrontOBJParser::object_name() {
  Object_nameContext *_localctx = _tracker.createInstance<Object_nameContext>(_ctx, getState());
  enterRule(_localctx, 58, WavefrontOBJParser::RuleObject_name);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(347);
    match(WavefrontOBJParser::T__33);
    setState(348);
    antlrcpp::downCast<Object_nameContext *>(_localctx)->name = match(WavefrontOBJParser::NAME);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- BevelContext ------------------------------------------------------------------

WavefrontOBJParser::BevelContext::BevelContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}


size_t WavefrontOBJParser::BevelContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleBevel;
}

void WavefrontOBJParser::BevelContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterBevel(this);
}

void WavefrontOBJParser::BevelContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitBevel(this);
}

WavefrontOBJParser::BevelContext* WavefrontOBJParser::bevel() {
  BevelContext *_localctx = _tracker.createInstance<BevelContext>(_ctx, getState());
  enterRule(_localctx, 60, WavefrontOBJParser::RuleBevel);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(350);
    match(WavefrontOBJParser::T__34);
    setState(351);
    _la = _input->LA(1);
    if (!(_la == WavefrontOBJParser::T__31

    || _la == WavefrontOBJParser::T__35)) {
    _errHandler->recoverInline(this);
    }
    else {
      _errHandler->reportMatch(this);
      consume();
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Color_interpolationContext ------------------------------------------------------------------

WavefrontOBJParser::Color_interpolationContext::Color_interpolationContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}


size_t WavefrontOBJParser::Color_interpolationContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleColor_interpolation;
}

void WavefrontOBJParser::Color_interpolationContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterColor_interpolation(this);
}

void WavefrontOBJParser::Color_interpolationContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitColor_interpolation(this);
}

WavefrontOBJParser::Color_interpolationContext* WavefrontOBJParser::color_interpolation() {
  Color_interpolationContext *_localctx = _tracker.createInstance<Color_interpolationContext>(_ctx, getState());
  enterRule(_localctx, 62, WavefrontOBJParser::RuleColor_interpolation);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(353);
    match(WavefrontOBJParser::T__36);
    setState(354);
    _la = _input->LA(1);
    if (!(_la == WavefrontOBJParser::T__31

    || _la == WavefrontOBJParser::T__35)) {
    _errHandler->recoverInline(this);
    }
    else {
      _errHandler->reportMatch(this);
      consume();
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Dissolve_interpolationContext ------------------------------------------------------------------

WavefrontOBJParser::Dissolve_interpolationContext::Dissolve_interpolationContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}


size_t WavefrontOBJParser::Dissolve_interpolationContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleDissolve_interpolation;
}

void WavefrontOBJParser::Dissolve_interpolationContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterDissolve_interpolation(this);
}

void WavefrontOBJParser::Dissolve_interpolationContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitDissolve_interpolation(this);
}

WavefrontOBJParser::Dissolve_interpolationContext* WavefrontOBJParser::dissolve_interpolation() {
  Dissolve_interpolationContext *_localctx = _tracker.createInstance<Dissolve_interpolationContext>(_ctx, getState());
  enterRule(_localctx, 64, WavefrontOBJParser::RuleDissolve_interpolation);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(356);
    match(WavefrontOBJParser::T__37);
    setState(357);
    _la = _input->LA(1);
    if (!(_la == WavefrontOBJParser::T__31

    || _la == WavefrontOBJParser::T__35)) {
    _errHandler->recoverInline(this);
    }
    else {
      _errHandler->reportMatch(this);
      consume();
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Level_of_detailContext ------------------------------------------------------------------

WavefrontOBJParser::Level_of_detailContext::Level_of_detailContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Level_of_detailContext::INTEGER() {
  return getToken(WavefrontOBJParser::INTEGER, 0);
}


size_t WavefrontOBJParser::Level_of_detailContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleLevel_of_detail;
}

void WavefrontOBJParser::Level_of_detailContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterLevel_of_detail(this);
}

void WavefrontOBJParser::Level_of_detailContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitLevel_of_detail(this);
}

WavefrontOBJParser::Level_of_detailContext* WavefrontOBJParser::level_of_detail() {
  Level_of_detailContext *_localctx = _tracker.createInstance<Level_of_detailContext>(_ctx, getState());
  enterRule(_localctx, 66, WavefrontOBJParser::RuleLevel_of_detail);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(359);
    match(WavefrontOBJParser::T__38);
    setState(360);
    antlrcpp::downCast<Level_of_detailContext *>(_localctx)->level = match(WavefrontOBJParser::INTEGER);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Map_libraryContext ------------------------------------------------------------------

WavefrontOBJParser::Map_libraryContext::Map_libraryContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<tree::TerminalNode *> WavefrontOBJParser::Map_libraryContext::FILENAME() {
  return getTokens(WavefrontOBJParser::FILENAME);
}

tree::TerminalNode* WavefrontOBJParser::Map_libraryContext::FILENAME(size_t i) {
  return getToken(WavefrontOBJParser::FILENAME, i);
}


size_t WavefrontOBJParser::Map_libraryContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleMap_library;
}

void WavefrontOBJParser::Map_libraryContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterMap_library(this);
}

void WavefrontOBJParser::Map_libraryContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitMap_library(this);
}

WavefrontOBJParser::Map_libraryContext* WavefrontOBJParser::map_library() {
  Map_libraryContext *_localctx = _tracker.createInstance<Map_libraryContext>(_ctx, getState());
  enterRule(_localctx, 68, WavefrontOBJParser::RuleMap_library);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(362);
    match(WavefrontOBJParser::T__39);
    setState(364); 
    _errHandler->sync(this);
    _la = _input->LA(1);
    do {
      setState(363);
      antlrcpp::downCast<Map_libraryContext *>(_localctx)->filename = match(WavefrontOBJParser::FILENAME);
      setState(366); 
      _errHandler->sync(this);
      _la = _input->LA(1);
    } while (_la == WavefrontOBJParser::FILENAME);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Use_mapContext ------------------------------------------------------------------

WavefrontOBJParser::Use_mapContext::Use_mapContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Use_mapContext::NAME() {
  return getToken(WavefrontOBJParser::NAME, 0);
}


size_t WavefrontOBJParser::Use_mapContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleUse_map;
}

void WavefrontOBJParser::Use_mapContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterUse_map(this);
}

void WavefrontOBJParser::Use_mapContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitUse_map(this);
}

WavefrontOBJParser::Use_mapContext* WavefrontOBJParser::use_map() {
  Use_mapContext *_localctx = _tracker.createInstance<Use_mapContext>(_ctx, getState());
  enterRule(_localctx, 70, WavefrontOBJParser::RuleUse_map);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(368);
    match(WavefrontOBJParser::T__40);
    setState(371);
    _errHandler->sync(this);
    switch (_input->LA(1)) {
      case WavefrontOBJParser::NAME: {
        setState(369);
        antlrcpp::downCast<Use_mapContext *>(_localctx)->map_name = match(WavefrontOBJParser::NAME);
        break;
      }

      case WavefrontOBJParser::T__31: {
        setState(370);
        match(WavefrontOBJParser::T__31);
        break;
      }

    default:
      throw NoViableAltException(this);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Material_libraryContext ------------------------------------------------------------------

WavefrontOBJParser::Material_libraryContext::Material_libraryContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Material_libraryContext::FILENAME() {
  return getToken(WavefrontOBJParser::FILENAME, 0);
}


size_t WavefrontOBJParser::Material_libraryContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleMaterial_library;
}

void WavefrontOBJParser::Material_libraryContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterMaterial_library(this);
}

void WavefrontOBJParser::Material_libraryContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitMaterial_library(this);
}

WavefrontOBJParser::Material_libraryContext* WavefrontOBJParser::material_library() {
  Material_libraryContext *_localctx = _tracker.createInstance<Material_libraryContext>(_ctx, getState());
  enterRule(_localctx, 72, WavefrontOBJParser::RuleMaterial_library);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(373);
    match(WavefrontOBJParser::T__41);
    setState(374);
    antlrcpp::downCast<Material_libraryContext *>(_localctx)->filename = match(WavefrontOBJParser::FILENAME);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Use_materialContext ------------------------------------------------------------------

WavefrontOBJParser::Use_materialContext::Use_materialContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Use_materialContext::NAME() {
  return getToken(WavefrontOBJParser::NAME, 0);
}


size_t WavefrontOBJParser::Use_materialContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleUse_material;
}

void WavefrontOBJParser::Use_materialContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterUse_material(this);
}

void WavefrontOBJParser::Use_materialContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitUse_material(this);
}

WavefrontOBJParser::Use_materialContext* WavefrontOBJParser::use_material() {
  Use_materialContext *_localctx = _tracker.createInstance<Use_materialContext>(_ctx, getState());
  enterRule(_localctx, 74, WavefrontOBJParser::RuleUse_material);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(376);
    match(WavefrontOBJParser::T__42);
    setState(377);
    match(WavefrontOBJParser::NAME);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Shadow_objectContext ------------------------------------------------------------------

WavefrontOBJParser::Shadow_objectContext::Shadow_objectContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Shadow_objectContext::FILENAME() {
  return getToken(WavefrontOBJParser::FILENAME, 0);
}


size_t WavefrontOBJParser::Shadow_objectContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleShadow_object;
}

void WavefrontOBJParser::Shadow_objectContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterShadow_object(this);
}

void WavefrontOBJParser::Shadow_objectContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitShadow_object(this);
}

WavefrontOBJParser::Shadow_objectContext* WavefrontOBJParser::shadow_object() {
  Shadow_objectContext *_localctx = _tracker.createInstance<Shadow_objectContext>(_ctx, getState());
  enterRule(_localctx, 76, WavefrontOBJParser::RuleShadow_object);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(379);
    match(WavefrontOBJParser::T__43);
    setState(380);
    antlrcpp::downCast<Shadow_objectContext *>(_localctx)->filename = match(WavefrontOBJParser::FILENAME);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Trace_objectContext ------------------------------------------------------------------

WavefrontOBJParser::Trace_objectContext::Trace_objectContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::Trace_objectContext::FILENAME() {
  return getToken(WavefrontOBJParser::FILENAME, 0);
}


size_t WavefrontOBJParser::Trace_objectContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleTrace_object;
}

void WavefrontOBJParser::Trace_objectContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterTrace_object(this);
}

void WavefrontOBJParser::Trace_objectContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitTrace_object(this);
}

WavefrontOBJParser::Trace_objectContext* WavefrontOBJParser::trace_object() {
  Trace_objectContext *_localctx = _tracker.createInstance<Trace_objectContext>(_ctx, getState());
  enterRule(_localctx, 78, WavefrontOBJParser::RuleTrace_object);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(382);
    match(WavefrontOBJParser::T__44);
    setState(383);
    antlrcpp::downCast<Trace_objectContext *>(_localctx)->filename = match(WavefrontOBJParser::FILENAME);
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Curve_approximation_techniqueContext ------------------------------------------------------------------

WavefrontOBJParser::Curve_approximation_techniqueContext::Curve_approximation_techniqueContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Curve_approximation_techniqueContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Curve_approximation_techniqueContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::Curve_approximation_techniqueContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleCurve_approximation_technique;
}

void WavefrontOBJParser::Curve_approximation_techniqueContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterCurve_approximation_technique(this);
}

void WavefrontOBJParser::Curve_approximation_techniqueContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitCurve_approximation_technique(this);
}

WavefrontOBJParser::Curve_approximation_techniqueContext* WavefrontOBJParser::curve_approximation_technique() {
  Curve_approximation_techniqueContext *_localctx = _tracker.createInstance<Curve_approximation_techniqueContext>(_ctx, getState());
  enterRule(_localctx, 80, WavefrontOBJParser::RuleCurve_approximation_technique);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(385);
    match(WavefrontOBJParser::T__45);
    setState(394);
    _errHandler->sync(this);
    switch (_input->LA(1)) {
      case WavefrontOBJParser::T__46: {
        setState(386);
        match(WavefrontOBJParser::T__46);
        setState(387);
        antlrcpp::downCast<Curve_approximation_techniqueContext *>(_localctx)->res = decimal();
        break;
      }

      case WavefrontOBJParser::T__47: {
        setState(388);
        match(WavefrontOBJParser::T__47);
        setState(389);
        antlrcpp::downCast<Curve_approximation_techniqueContext *>(_localctx)->maxlength = decimal();
        break;
      }

      case WavefrontOBJParser::T__19: {
        setState(390);
        match(WavefrontOBJParser::T__19);
        setState(391);
        antlrcpp::downCast<Curve_approximation_techniqueContext *>(_localctx)->maxdist = decimal();
        setState(392);
        antlrcpp::downCast<Curve_approximation_techniqueContext *>(_localctx)->maxangle = decimal();
        break;
      }

    default:
      throw NoViableAltException(this);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- Surface_approximation_techniqueContext ------------------------------------------------------------------

WavefrontOBJParser::Surface_approximation_techniqueContext::Surface_approximation_techniqueContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

std::vector<WavefrontOBJParser::DecimalContext *> WavefrontOBJParser::Surface_approximation_techniqueContext::decimal() {
  return getRuleContexts<WavefrontOBJParser::DecimalContext>();
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::Surface_approximation_techniqueContext::decimal(size_t i) {
  return getRuleContext<WavefrontOBJParser::DecimalContext>(i);
}


size_t WavefrontOBJParser::Surface_approximation_techniqueContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleSurface_approximation_technique;
}

void WavefrontOBJParser::Surface_approximation_techniqueContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterSurface_approximation_technique(this);
}

void WavefrontOBJParser::Surface_approximation_techniqueContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitSurface_approximation_technique(this);
}

WavefrontOBJParser::Surface_approximation_techniqueContext* WavefrontOBJParser::surface_approximation_technique() {
  Surface_approximation_techniqueContext *_localctx = _tracker.createInstance<Surface_approximation_techniqueContext>(_ctx, getState());
  enterRule(_localctx, 82, WavefrontOBJParser::RuleSurface_approximation_technique);

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(396);
    match(WavefrontOBJParser::T__48);
    setState(409);
    _errHandler->sync(this);
    switch (_input->LA(1)) {
      case WavefrontOBJParser::T__49: {
        setState(397);
        match(WavefrontOBJParser::T__49);
        setState(398);
        antlrcpp::downCast<Surface_approximation_techniqueContext *>(_localctx)->ures = decimal();
        setState(399);
        antlrcpp::downCast<Surface_approximation_techniqueContext *>(_localctx)->vres = decimal();
        break;
      }

      case WavefrontOBJParser::T__50: {
        setState(401);
        match(WavefrontOBJParser::T__50);
        setState(402);
        antlrcpp::downCast<Surface_approximation_techniqueContext *>(_localctx)->uvres = decimal();
        break;
      }

      case WavefrontOBJParser::T__47: {
        setState(403);
        match(WavefrontOBJParser::T__47);
        setState(404);
        antlrcpp::downCast<Surface_approximation_techniqueContext *>(_localctx)->maxlength = decimal();
        break;
      }

      case WavefrontOBJParser::T__19: {
        setState(405);
        match(WavefrontOBJParser::T__19);
        setState(406);
        antlrcpp::downCast<Surface_approximation_techniqueContext *>(_localctx)->maxdist = decimal();
        setState(407);
        antlrcpp::downCast<Surface_approximation_techniqueContext *>(_localctx)->maxangle = decimal();
        break;
      }

    default:
      throw NoViableAltException(this);
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

//----------------- DecimalContext ------------------------------------------------------------------

WavefrontOBJParser::DecimalContext::DecimalContext(ParserRuleContext *parent, size_t invokingState)
  : ParserRuleContext(parent, invokingState) {
}

tree::TerminalNode* WavefrontOBJParser::DecimalContext::DECIMAL() {
  return getToken(WavefrontOBJParser::DECIMAL, 0);
}

tree::TerminalNode* WavefrontOBJParser::DecimalContext::INTEGER() {
  return getToken(WavefrontOBJParser::INTEGER, 0);
}


size_t WavefrontOBJParser::DecimalContext::getRuleIndex() const {
  return WavefrontOBJParser::RuleDecimal;
}

void WavefrontOBJParser::DecimalContext::enterRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->enterDecimal(this);
}

void WavefrontOBJParser::DecimalContext::exitRule(tree::ParseTreeListener *listener) {
  auto parserListener = dynamic_cast<WavefrontOBJListener *>(listener);
  if (parserListener != nullptr)
    parserListener->exitDecimal(this);
}

WavefrontOBJParser::DecimalContext* WavefrontOBJParser::decimal() {
  DecimalContext *_localctx = _tracker.createInstance<DecimalContext>(_ctx, getState());
  enterRule(_localctx, 84, WavefrontOBJParser::RuleDecimal);
  size_t _la = 0;

#if __cplusplus > 201703L
  auto onExit = finally([=, this] {
#else
  auto onExit = finally([=] {
#endif
    exitRule();
  });
  try {
    enterOuterAlt(_localctx, 1);
    setState(411);
    _la = _input->LA(1);
    if (!(_la == WavefrontOBJParser::INTEGER

    || _la == WavefrontOBJParser::DECIMAL)) {
    _errHandler->recoverInline(this);
    }
    else {
      _errHandler->reportMatch(this);
      consume();
    }
   
  }
  catch (RecognitionException &e) {
    _errHandler->reportError(this, e);
    _localctx->exception = std::current_exception();
    _errHandler->recover(this, _localctx->exception);
  }

  return _localctx;
}

void WavefrontOBJParser::initialize() {
#if ANTLR4_USE_THREAD_LOCAL_CACHE
  wavefrontobjParserInitialize();
#else
  ::antlr4::internal::call_once(wavefrontobjParserOnceFlag, wavefrontobjParserInitialize);
#endif
}
