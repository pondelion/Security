COMPILER  = g++
CFLAGS    = -std=c++17 
INCLUDE   = -I./src -I"C:\clib\json\include"
TARGET    = ./out/bin/extractor.exe
SRCDIR    = ./src
rwildcard = $(foreach d,$(wildcard $1*),$(call rwildcard,$d/,$2) $(filter $(subst *,%,$2),$d))
SOURCES   = $(call rwildcard, ./src/*/, *.cc)
LIBDIR    = .\out\lib
LIBS      = -L$(LIBDIR) -lextractor

# OBJECTS   = $(addprefix $(LIBDIR)/, $(notdir $(SOURCES:.cc=.o)))
OBJECTS   = $(LIBDIR)/base-feature-extractor.o $(LIBDIR)/pe-file-extractor.o

$(warning OBJECTS = $(OBJECTS))

$(TARGET): $(OBJECTS) $(LIBDIR)/libextractor.a
	$(COMPILER) $(SRCDIR)/main.cc $(LIBS) $(INCLUDE) -o $(TARGET)

# $(LIBDIR)/%.o: $(SOURCES)
# 	$(COMPILER) $(INCLUDE) -o $@ -c $<

$(LIBDIR)/base-feature-extractor.o: $(SRCDIR)/base/base-feature-extractor.cc
	$(COMPILER) $(INCLUDE) -o $@ -c $< $(CFLAGS)

$(LIBDIR)/pe-file-extractor.o: $(SRCDIR)/executable/pe/pe-file-extractor.cc
	$(COMPILER) $(INCLUDE) -o $@ -c $< $(CFLAGS)

$(LIBDIR)/libextractor.a: $(OBJECTS)
	ar rcs $(LIBDIR)/libextractor.a $(OBJECTS)

all: clean $(TARGET)

clean:
	-del /q $(LIBDIR)\*.a $(LIBDIR)\*.o .\out\bin\*.exe
