![ASCII logo here](https://github.com/dongli/CodeMate/raw/master/docs/ascii_logo.png)

# Installation

**Download the command [here](https://github.com/dongli/CodeMate/raw/master/products/codemate)**,
and set the SHELL environment as (in BASH):

```BASH
export PATH=<pat/to/this/command>:$PATH
```

I near future, I will add a `update` operator (or subcommand) to semi-automatically
update the downloaded command. Be patient, or just knock me (<dongli@lasg.iap.ac.cn>).

# Donation

**CodeMate** is open-sourced and the author use his time to create it, so if you
find it really save your time, consider give a push to make **CodeMate** go further
please.

<a href='http://www.pledgie.com/campaigns/20230'><img alt='Click here to lend your support to: Funding for CodeMate and make a donation at www.pledgie.com !' src='http://www.pledgie.com/campaigns/20230.png?skin_name=chrome' border='0' /></a>

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

# Authors

* Li Dong <dongli@lasg.iap.ac.cn>
