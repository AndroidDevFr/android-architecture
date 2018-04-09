# Reactive MVVM Architecture for Android™

[![Kotlin](https://img.shields.io/badge/kotlin-1.2.31-519EF8.svg)](https://kotlinlang.org/)
[![Min SDK](https://img.shields.io/badge/min%20SDK-16-lightgrey.svg)](http://developer.android.com/about/dashboards/index.html#Platform)

On this sample I try to show you how to make a smooth android architecture on **Reactive Functionnal Programming** :rocket:

## Introduction

First thing, thanks [Kickstarter™](https://github.com/kickstarter/android-oss) to put your Android Project on open sources! This sample is based on their architecture.

The main goals of this sample are:
 * Show you how powerfull is the MVVM design pattern,  
 * Write code **Easy To Test**
 * Enjoy dependency injection

## Pattern

[MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) facilitates a separation of development of the graphical user interface from development of the business logic or back-end logic (the data model). The view model of MVVM is a value converter, meaning the view model is responsible for exposing (converting) the data objects from the model in such a way that objects are easily managed and presented. In this respect, the view model is more model than view, and handles most if not all of the view's display logic. The view model may implement a mediator pattern, organizing access to the back-end logic around the set of use cases supported by the view.

## Libraries

* [Retrofit2](http://square.github.io/retrofit/)
* [Gson](https://github.com/google/gson)
* [RxJava2](http://reactivex.io/)
* [AutoDispose](https://github.com/uber/AutoDispose)
* [Dagger2](https://google.github.io/dagger/)

License
--------

    Copyright 2018 AndroidDevFr, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
