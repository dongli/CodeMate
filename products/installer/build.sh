#!/bin/bash

cat stub.sh payload/codemate.jar > payload/codemate

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
