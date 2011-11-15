echo "Make native libraries"
ndk-build

echo "Make Java files and install on emulator"
ant debug install

echo "Goooooooo!"
