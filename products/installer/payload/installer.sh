#!/bin/bash

# create .codemate directory under the $HOME
CODEMATE_ROOT=$HOME/.codemate
if [ ! -d $CODEMATE_ROOT ]; then
    echo "Create $CODEMATE_ROOT"
    mkdir $CODEMATE_ROOT
else
    echo "Use existing $CODEMATE_ROOT"
    if [ ! -z "`ls $CODEMATE_ROOT`" ]; then
        echo "[Notice]: $CODEMATE_ROOT is not empty"
        ls -l $CODEMATE_ROOT
        if [ -e $CODEMATE_ROOT/codemate.jar ]; then
            VERSION=`java -jar $CODEMATE_ROOT/codemate.jar -v`
            echo "[Notice]: You have installed CodeMate $VERSION."
        fi
        echo "[Notice]: Clean them (y/n)?"
        read -p '> ' ans
        if [[ "$ans" != "n" ]]; then
            rm -r $CODEMATE_ROOT/*
        fi
    fi
fi

# copy files into $CODEMATE_ROOT
cp ./codemate.jar $CODEMATE_ROOT
cp ./setup.sh $CODEMATE_ROOT

if [[ "$SHELL" =~ "bash" ]]; then
    echo "[Notice]: Set BASH environment in $HOME/.bashrc."
    if ! grep 'source .*\.codemate/setup\.sh' $HOME/.bashrc > /dev/null; then
        echo -e "\n\n# Added by CodeMate\nsource $HOME/.codemate/setup.sh" >> $HOME/.bashrc
    fi
elif [[ "$SHELL" =~ "tcsh" ]]; then
    echo "[Notice]: Set TCSH environment."
    if ! grep 'source .*\.codemate/setup\.sh' $HOME/.bashrc > /dev/null; then
        echo -e "\n\n# Added by CodeMate\nsource $HOME/.codemate/setup.sh" >> $HOME/.cshrc
    fi
fi
