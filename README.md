# Kotlin No Build

The idea is that you do not need anything but a Kotlin compiler to build a
Kotlin project. No Gradle, no Maven, no Ant, etc. Only Kotlin compiler.

## This is an Experimental Project

First of all this project was created as a preparation for Stream in
Kotlin Meta. We will explore Kotlin Scripting and how it works. I'm not sure if
this is even a good idea myself. This is why I'm implementing it. This is a
research project. I'm not making any claims about suitability of this approach
to any project.

## It's likely Not Suitable for Your Project

If you are using Gradle with tons of modules to manage and find tons of
dependencies you probably don't want to use this tool. Knob is
more like writting shell scripts but in Kotlin.

## Advantages of Knob

No weird stuff about Gradle. Just Kotlin running Kotlin.

## Disadvantages of Knob

You need to be comfortable with Kotlin and implementing things yourself.

## Why is it called "no build" when it's clearly a build tool?

You know all these BS movements that supposedly remove the root cause of your
problems? Things like NoSQL, No-code, Serverless, etc. This is the same logic.
So there is no build anymore.

## How to use the library in your own project

See `example` folder.

## No Build in Other Languages

This is obviously applicable not only to Kotlin. You can implement the same
kind of approach for other languages (apart from the languages that support
this natively, of course). Here is few examples in the wild:

- C - https://github.com/tsoding/nob.h
- C++ - https://github.com/zhiayang/nabs
- Java - https://gitlab.com/NikaDev/arris

THE ORIGINAL IDEA WAS STOLEN FROM TSODING.
