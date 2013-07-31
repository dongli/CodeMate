#!/bin/bash

# create .codemate directory under the $HOME
CODEMATE_ROOT=$HOME/.codemate
if [ ! -d $CODEMATE_ROOT ]; then
    echo "[Notice]: Create $CODEMATE_ROOT"
    mkdir $CODEMATE_ROOT
else
    echo "[Notice]: Use existing $CODEMATE_ROOT"
    if [ ! -z "`ls $CODEMATE_ROOT`" ]; then
        echo "[Notice]: $CODEMATE_ROOT is not empty"
        ls -l $CODEMATE_ROOT
        if [ -e $CODEMATE_ROOT/codemate.jar ]; then
            VERSION=`java -jar $CODEMATE_ROOT/codemate.jar -v`
            if [ -e $CODEMATE_ROOT/install.info ]; then
                COMMIT_HASH=`awk '/prev_commit_hash/ { print $3; }' $CODEMATE_ROOT/install.info`
            fi
            echo "[Notice]: You have installed CodeMate $VERSION in commit $COMMIT_HASH."
        fi
    fi
fi

# copy files into $CODEMATE_ROOT
cp ./codemate.jar $CODEMATE_ROOT
cp ./codemate $CODEMATE_ROOT && chmod a+x $CODEMATE_ROOT/codemate
cp ./setup.sh $CODEMATE_ROOT
cp ./install.info $CODEMATE_ROOT

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
