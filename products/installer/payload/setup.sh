#!/bin/bash

export CODEMATE_ROOT=$(dirname $BASH_ARGV)
export PATH=$CODEMATE_ROOT:$PATH

# command line completion
function _codemate_()
{
    local prev_argv=${COMP_WORDS[COMP_CWORD-1]}
    local curr_argv=${COMP_WORDS[COMP_CWORD]}
    completed_words=""
    case "${prev_argv##*/}" in
    "codemate")
        completed_words="-silence help scan clean create"
        ;;
    "help")
        completed_words="scan"
        ;;
    "create")
        completed_words="template"
        ;;
    esac
    COMPREPLY=($(compgen -W "$completed_words" -- $curr_argv))
}

complete -o default -F _codemate_ codemate
