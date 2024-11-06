
# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

# Declares the project name. The project name can be accessed via ${ PROJECT_NAME},
# Since this is the top level CMakeLists.txt, the project name is also accessible
# with ${CMAKE_PROJECT_NAME} (both CMake variables are in-sync within the top level
# build script scope).
project("deviceaddresstweaker")
#set_property(SOURCE proot/src/loader/assembly-arm.h APPEND PROPERTY COMPILE_OPTIONS "-x" "assembler-with-cpp")
#set_property(SOURCE proot/src/loader/assembly-arm64.h APPEND PROPERTY COMPILE_OPTIONS "-x" "assembler-with-cpp")
#set_property(SOURCE proot/src/loader/assembly-x86.h APPEND PROPERTY COMPILE_OPTIONS "-x" "assembler-with-cpp")
#set_property(SOURCE proot/src/loader/assembly-x86_64.h APPEND PROPERTY COMPILE_OPTIONS "-x" "assembler-with-cpp")

find_library(ANDROID_LIBS log)

macro(SET_OPTION option value)
    set(${option} ${value} CACHE INTERNAL "" FORCE)
endmacro()

enable_language(CXX C ASM)
SET (ASM_OPTIONS "-x assembler-with-cpp")
SET (CMAKE_ASM_FLAGS "${CMAKE_C_FLAGS} ${ASM_OPTIONS}")
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_C_STANDARD 11)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Werror=format-invalid-specifier -Werror=return-type -Wno-invalid-offsetof")
if ("${CMAKE_CXX_COMPILER_ID}" MATCHES "Clang")
    SET(CLANG_CXX_EXTRA_OPT "-Werror=unknown-warning-option -Werror=format-invalid-specifier -Werror=call-to-pure-virtual-from-ctor-dtor")
    SET(CLANG_C_EXTRA_OPT "-Werror=format-invalid-specifier")
else ()
    SET(CLANG_CXX_EXTRA_OPT "")
    SET(CLANG_C_EXTRA_OPT "")
endif ()
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${CLANG_CXX_EXTRA_OPT} -fPIC -Werror=return-type -Wno-invalid-offsetof")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${CLANG_C_EXTRA_OPT} -fPIC -Werror=return-type")
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Wl,--no-allow-shlib-undefined,--no-undefined -Wl,-z,defs,-z,now,-z,relro")

SET_OPTION(DOBBY_GENERATE_SHARED OFF)
# Do not use Dobby symbol resolver. You link it once and it crashes everywhere.
SET_OPTION(Plugin.SymbolResolver OFF)
add_subdirectory(Dobby-master dobby)
add_subdirectory(libarchive-master archive)
# set -fomit-frame-pointer for Dobby if ANDROID_ABI is armeabi-v7a
if ("${ANDROID_ABI}" STREQUAL "armeabi-v7a")
    target_compile_options(dobby PRIVATE -fomit-frame-pointer)
endif ()
add_library(${CMAKE_PROJECT_NAME} SHARED
        deviceaddresstweaker.cpp
        deviceaddresstweaker.h
#        ${DOBBY_DIR}/examples/socket_example.cc
        datutils.cpp datutils.h
        android/sysPropSet.cpp android/sysPropSet.cpp
        android/nativehelper/JNIHelp.h android/nativehelper/JNIHelp.cpp
        android/nativehelper/JniInvocation.h android/nativehelper/JniInvocation.cpp
        android/nativehelper/ScopedLocalRef.h android/nativehelper/ScopedUtfChars.h
        android/nativehelper/ScopedPrimitiveArray.h
        android/nativehelper/JniConstants.cpp android/nativehelper/JniConstants.h
        android/nativehelper/ALog-priv.h
#        talloc/talloc.h talloc/talloc.cpp

#        proot/src/cli/care.c proot/src/cli/care.h
#        proot/src/cli/cli.c
#        proot/src/cli/cli.h
#        proot/src/cli/note.c
#        proot/src/cli/note.h
#        proot/src/cli/proot.c
#        proot/src/cli/proot.h
#        proot/src/execve/aoxp.c
#        proot/src/execve/aoxp.h proot/src/execve/auxv.c proot/src/execve/auxv.h
#        proot/src/execve/elf.c proot/src/execve/elf.h
#        proot/src/execve/enter.c
#        proot/src/execve/execve.h proot/src/execve/exit.c proot/src/execve/ldso.c
#        proot/src/execve/ldso.h proot/src/execve/shebang.c proot/src/execve/shebang.h
#        proot/src/extension/care/archive.c proot/src/extension/care/archive.h
#        proot/src/extension/care/care.c proot/src/extension/care/care.h
#        proot/src/extension/care/extract.c proot/src/extension/care/extract.h
#        proot/src/extension/care/final.c proot/src/extension/care/final.h proot/src/extension/fake_id0/fake_id0.c
#        proot/src/extension/kompat/kompat.c proot/src/extension/link2symlink/link2symlink.c proot/src/extension/portmap/map.c
#        proot/src/extension/portmap/portmap.c proot/src/extension/portmap/portmap.h
#        proot/src/extension/extension.c proot/src/extension/extension.h
#        proot/src/loader/assembly.S proot/src/loader/assembly-arm.h proot/src/loader/assembly-arm64.h
#        proot/src/loader/assembly-x86.h proot/src/loader/assembly-x86_64.h proot/src/loader/loader.c
#        proot/src/loader/script.h proot/src/path/binding.c proot/src/path/binding.h
#        proot/src/path/canon.c proot/src/path/canon.h proot/src/path/glue.c
#        proot/src/path/glue.h proot/src/path/path.c proot/src/path/path.h
#        proot/src/path/proc.c proot/src/path/proc.h proot/src/path/temp.c
#        proot/src/path/temp.h proot/src/ptrace/ptrace.c proot/src/ptrace/ptrace.h
#        proot/src/ptrace/user.c proot/src/ptrace/user.h proot/src/ptrace/wait.c
#        proot/src/ptrace/wait.h proot/src/syscall/chain.c proot/src/syscall/chain.h
#
#        proot/src/syscall/enter.c proot/src/syscall/exit.c proot/src/syscall/heap.c
#        proot/src/syscall/heap.h proot/src/syscall/rlimit.c proot/src/syscall/rlimit.h
#        proot/src/syscall/seccomp.c proot/src/syscall/seccomp.h proot/src/syscall/socket.c
#        proot/src/syscall/socket.h proot/src/syscall/syscall.c proot/src/syscall/syscall.h
#        proot/src/syscall/sysnum.c proot/src/syscall/sysnum.h proot/src/syscall/sysnums.list
#        proot/src/syscall/sysnums-arm.h proot/src/syscall/sysnums-arm64.h proot/src/syscall/sysnums-i386.h
#        proot/src/syscall/sysnums-sh4.h proot/src/syscall/sysnums-x32.h proot/src/syscall/sysnums-x86_64.h
#        proot/src/tracee/abi.h proot/src/tracee/event.c proot/src/tracee/event.h
#        proot/src/tracee/mem.c proot/src/tracee/mem.h proot/src/tracee/reg.c
#        proot/src/tracee/reg.h proot/src/tracee/tracee.c proot/src/tracee/tracee.h
#        proot/src/.check_process_vm.c proot/src/.check_seccomp_filter.c proot/src/arch.h
#        proot/src/attribute.h proot/src/compat.h

)
target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        android
        log
        dobby
        archive
)