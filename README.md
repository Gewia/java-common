![GitHub](https://img.shields.io/github/license/E-edu/java-common)
![GitHub top language](https://img.shields.io/github/languages/top/E-edu/java-common)

![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/E-edu/java-common)

![GitHub last commit (branch)](https://img.shields.io/github/last-commit/E-Edu/java-common/master?label=last%20commit%20%28master%29)
![GitHub last commit (branch)](https://img.shields.io/github/last-commit/E-Edu/java-common/experimental?label=last%20commit%20%28experimental%29)

# Gewia Common

This project contains the commonly shared code for all our
java based micro services (e.g. task and gateway).

This repository also is our reference implementation for some things.

## Scope
![Maven Central](https://img.shields.io/maven-central/v/com.gewia.common/scope)

[Based on this meeting changelog](https://github.com/E-Edu/concept/blob/master/changelog/meeting/20200420-conceptmeeting.md#8-roles--permissions)

We have a custom implementation of the widely known "scopes" used in OAuth2 for example.

This implementation standardized scopes for
every Gewia related project - so this is the reference implementation.

Some special features:
- Implementations with and without restrictions
    - BasicScope (no restrictions)
    - MicroServiceScope
        - microService.topic.mode.scope.extra
- Merging (and de-merging)
    - user.email.read+write+delete.all+beta
- Pre-defined scopes
    - none (default)
    - own
    - self (only applicable to topics regarding a user itself)
    - all
    - beta
- Pre-defined modes
    - read
    - write
    - delete

## License

This project is licensed under the MIT license.
