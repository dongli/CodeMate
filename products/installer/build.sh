#!/bin/bash

# preparation
# - prepare the JAR file
which ant > /dev/null
if [[ $? != 0 ]]; then
    echo "[Error]: build.sh: Failed to find 'ant' command!"
    exit 1
fi
ant -f create_jar.xml
# - prepare the standalone command
cat stub.sh payload/codemate.jar > payload/codemate
# - prepare the previous GIT commit hash for identifying commits
echo "prev_commit_hash = $(git log -1 --pretty="%H")" > payload/install.info

cd payload
tar czf ../payload.tar.gz ./*
cd ..

if [ -e "payload.tar.gz" ]; then
    cat decompress.sh payload.tar.gz > codemate.installer
    chmod a+x codemate.installer
    rm payload.tar.gz
else
    echo "[Error]: build.sh: Failed to create payload.tar.gz"
    exit 1
fi

echo "[Notice]: build.sh: codemate.installer is created"
exit 0
