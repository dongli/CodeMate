#!/bin/bash

# preparation
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
    echo "Failed to create payload.tar.gz"
    exit 1
fi

echo "codemate.installer is created"
exit 0
