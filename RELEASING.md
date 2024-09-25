# Releasing

1. Update the version in top-level `gradle.properties`.
2. Update the `CHANGELOG.md` for the impending release.
3. `git commit -am "Prepare for next release."` and push the commit to remote repo.
4. `git tag X.Y.Z` (where X.Y.Z is the new version that follows SemVer scheme)
5. `git push origin X.Y.Z` and CI will handle the rest.
