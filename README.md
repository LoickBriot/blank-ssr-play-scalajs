
# blank-ssr-play-scalajs

Before this, run

```
cd custom_jars
bash install.sh
```


In development, to launch it:

```
PROD_VERSION= sbt server/run
```

In production:


```
sbt fullOptJS
-- Rename the file in server/public/javascripts with the right version, for example 1.0.1
sbt server/stage
PROD_VERSION=1.0.1 . cd server/target/universal/stage/bin/server -no-version-check -Dplay.http.secret.key='QCY?tAnfk?aZ?iwrNwnxIlR6CTf:G3gf:90Latabg@5241AB`R5W:1uDFN];Ik@n'
```
