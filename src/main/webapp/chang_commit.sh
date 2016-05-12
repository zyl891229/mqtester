#!/bin/sh
export LANG="en_US.UTF-8"
#传递3个参数
name=$1
commit=$2
path=$3

sed -i "" -e ":begin; /<td id=\"$1\">/,/<\/td>/ { /<\/td>/! { $! { N; b begin }; }; s#\(<td id=\"$1\">\).*\(<\/td>\)#\1$2\2#; };" $3