![ASCII logo here](https://github.com/dongli/CodeMate/raw/master/docs/ascii_logo.png)

# Installation

Download the installer [**here**](https://github.com/dongli/CodeMate/raw/master/products/installer/codemate.installer),
run it in the shell:

```
$ chmod a+x codemate.installer
$ ./codemate.installer
```

then **codemate** should be ready to go!

Since **CodeMate** will updated continuously, keeping your installed version up to date will be convenient. So an operator *update* is added, you can invoke it as:

```
$ codemate update
```

then **CodeMate** will be updated if necessary.

# Donation

**CodeMate** is open-sourced and the author uses his time to create it, so if you find it really saves your time, consider give a push to make **CodeMate** go further please.

<a href='https://pledgie.com/campaigns/20230'><img alt='Click here to lend your support to: Funding for CodeMate and make a donation at pledgie.com !' src='https://pledgie.com/campaigns/20230.png?skin_name=chrome' border='0' ></a> [![](http://t0.gstatic.com/images?q=tbn:ANd9GcSTcB66mXRvUWCRRrRUTEz2ZX43ZqgfvOxcTq-0K4QklAbeE2nG1g)](https://me.alipay.com/dongli1985)

# Description

CodeMate is a language tool suite for reducing the coding burden of scientific
programmers who use Fortran language, since other C/C++, Java programmers
already get fantastic tools. So I create CodeMate which can parse Fortran codes,
generate dependency relationship, build codes, link external libraries. All of
these should be automatic. In addition, I also add a practical template
mechanism to Fortran, since template can really save a lot of typing for us.

Previously, I have rewritten CodeMate three times, from BASH to Ruby. Right
now, I switch to Java, because of the indispensable parser generator ANTLR
(currently in its 4th version). It is more efficient and powerful than other
generators I have tried. In addition, ANTLR is better supported and more active.

**I will update this README continuously.** You can also check out the [wiki](https://github.com/dongli/CodeMate/wiki).

# Command Usage

The final product of **CodeMate** is just a command `codemate`. You can invoke
it in Linux/Mac terminal as:

```
codemate help
```

This will print the basic usage of it. When first called, CodeMate will ask for
some information about *compiler* and *libraries*, and create the configuration file
in `~/.codemate/config`. For scanning a Fortran project, use `scan` operator

```
codemate scan <project root>
```

It will scan the Fortran codes in that directory and its subdirectories, and
generate a `Makefile` for building the project.

# Template in Fortran

The template is expressed by writing a Java class which implements an interface
*TemplateBundle*.

```Java
package codemate.Fortran;

import java.util.*;

public interface TemplateBundle {
    public boolean containsTemplate(String templateName);

    public Map<Template, String> instantiate(String templateName,
            List<String> args, String block);
}
```

In method `instantiate`, you can write any logics and return expanded Fortran code
snippet based on the inputed `args` and `block` with the full power of Java.

When you want to create a new template class, you can call

```
codemate create template <template class>
```

A template of template class will be generated.

# Documentation

I am writing a [manual](https://github.com/dongli/CodeMate/raw/master/docs/CodeMate.pdf) 
for **CodeMate** (not completed), and you may also find some useful information
in the [wiki](https://github.com/dongli/CodeMate/wiki).

# Authors

* Li Dong <dongli@lasg.iap.ac.cn>
