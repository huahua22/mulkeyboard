cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
SET(DIR_SRC ./src/main/cpp)
FOREACH (next_ITEM ${DIR_SRC})
    aux_source_directory(${next_ITEM} SRC_INCLUDE)
    list(APPEND ALL_SRC ${SRC_INCLUDE})
ENDFOREACH (next_ITEM ${DIR_SRC})
#导入第三方依赖库
add_library(
        usb1.0
        SHARED
        IMPORTED
)
set_target_properties(
        usb1.0
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libusb1.0.so
)


find_library( # Sets the name of the path variable.
        log-lib
        log)

add_library( # Sets the name of the library.
        cp
        SHARED
        ${ALL_SRC})
target_link_libraries( # Specifies the target library.
        cp
        usb1.0
        ${log-lib})