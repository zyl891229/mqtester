:begin;
/<td id="aabbcc.war">/,/<\/td>/ {
	/<\/td>/! {
		$! {
			N;
				b begin;
			};
		};
		s#\(<td id="aabbcc.war">\).*\(<\/td>\)#\1COMMENT\2#g;
};
