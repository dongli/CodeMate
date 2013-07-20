# Description

CodeMate is a language tool suite for reducing the coding burden of scientific
programmers who use Fortran language, since other C/C++, Java programmers
already get fantastic tools. So I create CodeMate which can parse Fortran codes,
generate dependency relationship, build codes, link external libraries. All of
these should be automatic. In addition, I also add a practical template
mechanism to Fortran, since template can really save a lot of typing for us.

Previously, I have rewritten CodeMate three times, from BASH to Ruby. Right
now, I switch to Java, because the indispensable parser generator ANTLR
(currently in its 4th version). It is more efficient and powerful than other
generators I have tried. In addition, ANTLR is better supported and more active.

I will update this README continuously.

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

In method `instantiate`, you can write any logics and return expanded Fortran code snippet based on the inputted `args` and `block` with the full power of Java

# Authors

* Li Dong <dongli@lasg.iap.ac.cn>
