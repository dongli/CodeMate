# Description

CodeMate is a language tool suite for reducing the coding burden of scientific
programmers who use Fortran language, since other C/C++, Java programmers
already get fantastic tools. So I create CodeMate which can parse Fortran
codes, generate dependency relationship, build codes, link external libraries.
All of these should be automatic. In addition, I also add a practical
templating mechanism to Fortran, since templating can really save a lot of
typing for us.

Previously, I have rewritten CodeMate three times, from BASH to Ruby. Right
now, I switch to Java, because the indispensible parser generator ANTLR
(currently in its 4th version). It is more efficient than other generators I
have tried.

I will be update this README continuously.

# Authors

* Li Dong <dongli@lasg.iap.ac.cn>
