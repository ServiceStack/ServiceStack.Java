echo off
if "%1" == "" (
echo Provide a version number in the form x.x.x
) else (
mvn tycho-versions:set-version -DnewVersion=%1 -Dartifacts=net.servicestack.eclipse,ServiceStackEclipse.Repository,net.servicestack.eclipse.feature
)