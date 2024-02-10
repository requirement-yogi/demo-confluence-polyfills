![Logo](docs/images/logo.png)

# Demo project for others on the Marketplace

In the wake of Confluence 8.8, I hear that some people are in distress to manage the compatibility
with all versions of Confluence. I'm providing here 2-3 examples that may help. **My code is not the
best**, but it works with C7.19, C8.5.4 and C8.8.

The genius parts:
- The Confluence Polyfills: Being able to program against Confluence 7.19 and 8.5 at compile-time, with
  real compile-time dependencies to actual methods of Confluence, and not using the Reflection API is awesome.
  Then we ship **all** of those classes into our final jar, and the bean initializer chooses the implementation
  depending on our detected version of Confluence.
- The polyfills depend on AutowireCapableBeanFactory ([here]([PermissionAdaptorFactoryBean.java](libs%2Futils-confluence%2Fsrc%2Fmain%2Fjava%2Fcom%2Frequirementyogi%2Fserver%2Futils%2Fconfluence%2Fcompat%2FPermissionAdaptorFactoryBean.java)))
  to autowire components on-demand, it's convenient,
- All plugins and libraries depend on [libs/confluence-parent-pom.xml](libs/confluence-parent-pom.xml),
  so we set values once-for-all.


### What to look at

- The final plugin is [requirementyogi/pom.xml](requirementyogi/pom.xml) (we have other plugins, it's always the same pom.xml),
- All plugins have a dependency to [libs/utils-confluence/pom.xml](libs/utils-confluence/pom.xml), which is our small utils
  library for all our Confluence plugins,
- That lib depends on all of the polyfills:
  - [libs/confluence-polyfills-850/pom.xml](libs/confluence-polyfills-850/pom.xml) for Confluence 8.5.0,
  - [libs/confluence-polyfills-720/pom.xml](libs/confluence-polyfills-720/pom.xml) for Confluence 7.20,
  - [libs/confluence-polyfills-719/pom.xml](libs/confluence-polyfills-719/pom.xml) for Confluence 7.19,
- All polyfills depend on [libs%2Fconfluence-polyfills-base%2Fpom.xml](libs%2Fconfluence-polyfills-base%2Fpom.xml),
  which contains the interfaces of services which are implemented in the polyfills.
- All of those, plugins and libraries together, depend on a [libs/confluence-parent-pom.xml](libs/confluence-parent-pom.xml),
  which declares _provided_ libraries that our plugins may need (Confluence, Active Objects, UPM licensing, etc.).

### Out of scope

What this repository doesn't have:
- We don't publish our source code,
- It clearly doesn't compile at all, those are just excerpts of files,
- No compatibility with Confluence 9.0.
- No usage of `confluence-plugin-platform-pom`, which is the new recommended thing. Instead we just rely on the direct
  `confluence` dependency (in other words, `confluence.jar` and its dependencies), which is supposed to be a malpractice but works
  really well, at least up to Confluence 8.8.
- No Spring Scanner (I know I should, but the current system doesn't bother me),
- No NPM Stack, because we don't have a good system. **Please help us and show us your stack**!
- No, I'm serious, we need help to build our NPM stack, we don't have the Hot Module Reload, we use Atlassian WRM
  and it does strange things.

# Other partners are also providing helping resources!

And, surprise, their code is much better prepared to go public! Go have a look at:

- https://github.com/collabsoft-net/example-jira-app-with-docker-compose
- In general, Collabsoft is heroic in providing resources for fellow vendors: https://github.com/collabsoft-net
- I think someone even published an entire stack for their DC apps, please give me the URL!