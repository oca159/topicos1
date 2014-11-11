% dijkstra(Vertex0, Ss) is true if Ss is a list of structures s(Vertex, Dist,
%   Path) containing the shortest Path from Vertex0 to Vertex, the distance of
%   the path being Dist.  The graph is defined by e/3.
% e.g. dijkstra(penzance, Ss)

dijkstra(Vertex, Ss):-
  create(Vertex, [Vertex], Ds),
  dijkstra_1(Ds, [s(Vertex,0,[])], Ss).

dijkstra_1([], Ss, Ss).
dijkstra_1([D|Ds], Ss0, Ss):-
  best(Ds, D, S),
  delete([D|Ds], [S], Ds1),
  S=s(Vertex,Distance,Path),
  reverse([Vertex|Path], Path1),
  merge(Ss0, [s(Vertex,Distance,Path1)], Ss1),
  create(Vertex, [Vertex|Path], Ds2),
  delete(Ds2, Ss1, Ds3),
  incr(Ds3, Distance, Ds4),
  merge(Ds1, Ds4, Ds5),
  dijkstra_1(Ds5, Ss1, Ss).

% path(Vertex0, Vertex, Path, Dist) is true if Path is the shortest path from
%   Vertex0 to Vertex, and the length of the path is Dist. The graph is defined
%   by e/3.
% e.g. path(penzance, london, Path, Dist)
path(Vertex0, Vertex, Path, Dist):-
  dijkstra(Vertex0, Ss),
  member(s(Vertex,Dist,Path), Ss), !, write(Path).


% create(Start, Path, Edges) is true if Edges is a list of structures s(Vertex,
%   Distance, Path) containing, for each Vertex accessible from Start, the
%   Distance from the Vertex and the specified Path.  The list is sorted by the
%   name of the Vertex.
create(Start, Path, Edges):-
  setof(s(Vertex,Edge,Path), e(Start,Vertex,Edge), Edges), !.
create(_, _, []).

% best(Edges, Edge0, Edge) is true if Edge is the element of Edges, a list of
%   structures s(Vertex, Distance, Path), having the smallest Distance.  Edge0
%   constitutes an upper bound.
best([], Best, Best).
best([Edge|Edges], Best0, Best):-
  shorter(Edge, Best0), !,
  best(Edges, Edge, Best).
best([_|Edges], Best0, Best):-
  best(Edges, Best0, Best).

shorter(s(_,X,_), s(_,Y,_)):-X < Y.

% delete(Xs, Ys, Zs) is true if Xs, Ys and Zs are lists of structures s(Vertex,
%   Distance, Path) ordered by Vertex, and Zs is the result of deleting from Xs
%   those elements having the same Vertex as elements in Ys.
delete([], _, []).
delete([X|Xs], [], [X|Xs]):-!.
delete([X|Xs], [Y|Ys], Ds):-
  eq(X, Y), !,
  delete(Xs, Ys, Ds).
delete([X|Xs], [Y|Ys], [X|Ds]):-
  lt(X, Y), !, delete(Xs, [Y|Ys], Ds).
delete([X|Xs], [_|Ys], Ds):-
  delete([X|Xs], Ys, Ds).

% merge(Xs, Ys, Zs) is true if Zs is the result of merging Xs and Ys, where Xs,
%   Ys and Zs are lists of structures s(Vertex, Distance, Path), and are
%   ordered by Vertex.  If an element in Xs has the same Vertex as an element
%   in Ys, the element with the shorter Distance will be in Zs.
merge([], Ys, Ys).
merge([X|Xs], [], [X|Xs]):-!.
merge([X|Xs], [Y|Ys], [X|Zs]):-
  eq(X, Y), shorter(X, Y), !,
  merge(Xs, Ys, Zs).
merge([X|Xs], [Y|Ys], [Y|Zs]):-
  eq(X, Y), !,
  merge(Xs, Ys, Zs).
merge([X|Xs], [Y|Ys], [X|Zs]):-
  lt(X, Y), !,
  merge(Xs, [Y|Ys], Zs).
merge([X|Xs], [Y|Ys], [Y|Zs]):-
  merge([X|Xs], Ys, Zs).

eq(s(X,_,_), s(X,_,_)).

lt(s(X,_,_), s(Y,_,_)):-X @< Y.

% incr(Xs, Incr, Ys) is true if Xs and Ys are lists of structures s(Vertex,
%   Distance, Path), the only difference being that the value of Distance in Ys
%   is Incr more than that in Xs.
incr([], _, []).
incr([s(V,D1,P)|Xs], Incr, [s(V,D2,P)|Ys]):-
  D2 is D1 + Incr,
  incr(Xs, Incr, Ys).

% member(X, Ys) is true if the element X is contained in the list Ys.
%member(X, [X|_]).
%member(X, [_|Ys]):-member(X, Ys).

% reverse(Xs, Ys) is true if Ys is the result of reversing the order of the
%   elements in the list Xs.
%reverse(Xs, Ys):-reverse_1(Xs, [], Ys).

%reverse_1([], As, As).
%reverse_1([X|Xs], As, Ys):-reverse_1(Xs, [X|As], Ys).

e(X, Y, Z):-dist(X, Y, Z).
e(X, Y, Z):-dist(Y, X, Z).


%%%%%linea1%%%%%%%%
dist(observatorio, tacubaya,1.47652166469724).
dist(tacubaya, juanacatlan, 0.5734519178351764).
dist(juanacatlan, chapultepec, 0.5973386026830494).
dist(chapultepec, sevilla, 0.7073508157614314).
dist(sevilla, insurgentes, 0.8230197978469072).
dist(insurgentes, cuauhtemoc,0.9429928073570758).
dist(cuauhtemoc, balderas,0.6301413019318206).
dist(balderas, salto_del_agua,0.758287523172765).
dist(salto_del_agua, isabel_la_catolica,0.5031765452336948).
dist(isabel_la_catolica, pino_suarez,0.5275305596705688).
dist(pino_suarez, merced,0.9227912427496711).
dist(merced, candelaria,0.5727702009726973).
dist(candelaria, san_lazaro,0.5204076061982988).
dist(san_lazaro, moctezuma,0.5059278187475528).
dist(moctezuma, balbuena,0.8920943999822051).
dist(balbuena, blvd_puerto_aereo,0.7086353494873293).
dist(blvd_puerto_aereo, gomez_farias,0.6248299538966746).
dist(gomez_farias, zaragoza,0.8853023558670671).
dist(zaragoza, pantitlan,1.1433664040206437).

dist(tacubaya,observatorio,1.47652166469724).
dist(juanacatlan,tacubaya,0.5734519178351764).
dist(chapultepec,juanacatlan,0.5973386026830494).
dist(sevilla, chapultepec,0.7073508157614314).
dist(insurgentes,sevilla,0.8230197978469072).
dist(cuauhtemoc,insurgentes,0.9429928073570758).
dist(balderas,cuauhtemoc,0.6301413019318206).
dist(salto_del_agua,balderas,0.758287523172765).
dist(isabel_la_catolica,salto_del_agua,0.5031765452336948).
dist(pino_suarez,isabel_la_catolica,0.5275305596705688).
dist(merced,pino_suarez,0.9227912427496711).
dist(candelaria,merced,0.5727702009726973).
dist(san_lazaro,candelaria,0.5204076061982988).
dist(moctezuma,san_lazaro,0.5059278187475528).
dist(balbuena,moctezuma,0.8920943999822051).
dist(blvd_puerto_aereo,balbuena,0.7086353494873293).
dist(gomez_farias,blvd_puerto_aereo,0.6248299538966746).
dist(zaragoza,gomez_farias,0.8853023558670671).
dist(pantitlan,zaragoza,1.1433664040206437).

%%%%%%%%%Linea 2%%%%%%%%%%%%%%%%%%
dist(cuatro_caminos, panteones,1.416477430954002).
dist(panteones, tacuba,1.5500080206894067).
dist(tacuba, cuitlahuac,0.7971473005423112).
dist(cuitlahuac, popotla,0.8208318500817434).
dist(popotla, colegio_militar,0.27627909069364087).
dist(colegio_militar, normal,0.5464891787305306).
dist(normal, san_cosme,0.7021534085519073).
dist(san_cosme, revolucion,0.0).
dist(revolucion, hidalgo,1.5567059014737785).
dist(hidalgo, bellas_artes,0.5737064953460773).
dist(bellas_artes, allende,0.5045610003937777).
dist(allende, zocalo,0.5023231569501069).
dist(zocalo, pino_suarez,0.1396366276542486).
dist(pino_suarez, san_antonio_abad,0.17880824100843754).
dist(san_antonio_abad, chabacano,0.2792580472064459).
dist(chabacano, viaducto,0.16072558974199808).
dist(viaducto, xola,0.1337001241146403).
dist(xola, villa_de_cortes,0.19748727968586774).
dist(nativitas, villa_de_cortes,0.19669947142784927).
dist(nativitas, portales,0.2297515160755839).
dist(portales, ermita,0.20440037077710282).
dist(ermita, general_anaya,0.27494973505073117).
dist(general_anaya, tasquena,0.3034264980380946).
dist(tasquena, indios_verdes,3.71012526738006580).


dist(panteones, cuatro_caminos, 1.416477430954002).
dist(tacuba, panteones, 1.5500080206894067).
dist(cuitlahuac, tacuba, 0.7971473005423112).
dist(popotla, cuitlahuac, 0.8208318500817434).
dist(colegio_militar, popotla, 0.27627909069364087).
dist(normal, colegio_militar, 0.5464891787305306).
dist(san_cosme, normal, 0.7021534085519073).
dist(revolucion, san_cosme, 0.0).
dist(hidalgo, revolucion, 1.5567059014737785).
dist(bellas_artes, hidalgo, 0.5737064953460773).
dist(allende, bellas_artes, 0.5045610003937777).
dist(zocalo, allende, 0.5023231569501069).
dist(pino_suarez, zocalo, 0.1396366276542486).
dist(san_antonio_abad, pino_suarez, 0.17880824100843754).
dist(chabacano, san_antonio_abad, 0.2792580472064459).
dist(viaducto, chabacano, 0.16072558974199808).
dist(xola, viaducto, 0.1337001241146403).
dist(villa_de_cortes, xola, 0.19748727968586774).
dist(villa_de_cortes, nativitas, 0.19669947142784927).
dist(portales, nativitas, 0.2297515160755839).
dist(ermita, portales, 0.20440037077710282).
dist(general_anaya, ermita, 0.27494973505073117).
dist(tasquena, general_anaya, 0.3034264980380946).
dist(indios_verdes, tasquena, 3.71012526738006580).

%%%%%%%%%Linea 3%%%%%%%%%%%%%%%%%%
dist(indios_verdes, deportivo_18_marzo,0.8130361480406992).
dist(deportivo_18_marzo, potrero,0.6362691004585853).
dist(potrero, la_raza,0.5378854323429286).
dist(la_raza, tlatelolco,0.7106060432120149).
dist(tlatelolco, guerrero,0.33471489951994116).
dist(guerrero, hidalgo,0.23578564697371443).
dist(hidalgo, juarez,0.10557677668406676).
dist(juarez, balderas,0.17131427217321804).
dist(balderas, niños_heroes,0.22163674923411095).
dist(niños_heroes, hospital_general,0.38193233413682437).
dist(hospital_general, centro_medico,0.24104772093518373).
dist(centro_medico, etiopia,0.2036182536512104).
dist(etiopia, eugenia,0.2036182536512104).
dist(eugenia, division_norte,0.5854050911784134).
dist(division_norte, zapata,0.6914078387304124).
dist(zapata, coyoacan,0.6632586396502993).
dist(coyoacan, viveros,0.6091825431087902).
dist(viveros, miguel_aleman_quevedo,0.568230681284598).
dist(miguel_aleman_quevedo, copilco,0.5209449185408903).
dist(copilco, univerisidad,0.3606413426858412).


dist(deportivo_18_marzo,indios_verdes,0.8130361480406992).
dist(potrero,deportivo_18_marzo,0.6362691004585853).
dist(la_raza,potrero,0.5378854323429286).
dist(tlatelolco,la_raza,0.7106060432120149).
dist(guerrero,tlatelolco,0.33471489951994116).
dist(hidalgo,guerrero,0.23578564697371443).
dist(juarez,hidalgo,0.10557677668406676).
dist(balderas,juarez,0.17131427217321804).
dist(niños_heroes,balderas,0.22163674923411095).
dist(hospital_general,niños_heroes,0.38193233413682437).
dist(centro_medico,hospital_general,0.24104772093518373).
dist(etiopia,centro_medico,0.2036182536512104).
dist(eugenia,etiopia,0.2036182536512104).
dist(division_norte,eugenia,0.5854050911784134).
dist(zapata,division_norte,0.6914078387304124).
dist(coyoacan,zapata,0.6632586396502993).
dist(viveros,coyoacan,0.6091825431087902).
dist(miguel_aleman_quevedo,viveros,0.568230681284598).
dist(copilco,miguel_aleman_quevedo,0.5209449185408903).
dist(univerisidad,copilco,0.3606413426858412).

%%%%%%%%Linea 4%%%%%%%%%%%%%%%%%%
dist(martin_carrera, talisman,0.44216362123625635).
dist(talisman, bandojito,0.47066073097477806).
dist(bandojito, consulado,0.2491891092798048).
dist(consulado, canal_del_norte,0.3252333039952005).
dist(canal_del_norte, morelos,0.2647191368484236).
dist(morelos, candelaria,0.22586084282163502).
dist(candelaria, fray_servando,0.17088488706756694).
dist(fray_servando, jamaica,0.2868881222516853).
dist(jamaica, santa_anita,0.12234482541210005).


dist(talisman,martin_carrera,0.44216362123625635).
dist(bandojito,talisman,0.47066073097477806).
dist(consulado,bandojito,0.2491891092798048).
dist(canal_del_norte,consulado,0.3252333039952005).
dist(morelos,canal_del_norte,0.2647191368484236).
dist(candelaria,morelos,0.22586084282163502).
dist(fray_servando,candelaria,0.17088488706756694).
dist(jamaica,fray_servando,0.2868881222516853).
dist(santa_anita,jamaica,0.12234482541210005).

%%%%%%%%%Linea 5%%%%%%%%%%%%%%%%%%
dist(politecnico, inst_del_petroleo,0.4903373539748947).
dist(inst_del_petroleo, autobuses_del_norte,0.5435302952534087).
dist(autobuses_del_norte, la_raza,0.4476577646014563).
dist(la_raza, misterios,0.6878553634146785).
dist(misterios, valle_gomez,1.2805883746808067).
dist(valle_gomez, consulado,0.5962625193015045).
dist(consulado, eduardo_molina,0.9512723122502271).
dist(eduardo_molina, aragon,1.0610255190148665).
dist(aragon, oceania,0.9659634174157824).
dist(oceania, terminal_aerea,0.21488238416878117).
dist(terminal_aerea, hangares,0.16942348847242505).
dist(hangares, pantitlan,1.7142597424778552).


dist(inst_del_petroleo,politecnico,0.4903373539748947).
dist(autobuses_del_norte,inst_del_petroleo,0.5435302952534087).
dist(la_raza,autobuses_del_norte,0.4476577646014563).
dist(misterios,la_raza,0.6878553634146785).
dist(valle_gomez,misterios,1.2805883746808067).
dist(consulado,valle_gomez,0.5962625193015045).
dist(eduardo_molina,consulado,0.9512723122502271).
dist(aragon,eduardo_molina,1.0610255190148665).
dist(oceania,aragon,0.9659634174157824).
dist(terminal_aerea,oceania,0.21488238416878117).
dist(hangares,terminal_aerea,0.16942348847242505).
dist(pantitlan,hangares,1.7142597424778552).
%%%%%%%%%Linea 6%%%%%%%%%%%%%%%%%%
dist(el_rosario, tezozomoc,0.4474430308568442).
dist(tezozomoc, azcapotzalco,1.099248498394835).
dist(azcapotzalco, ferreria,1.3974049427093864).
dist(ferreria, norte_45,1.2309193592242682).
dist(norte_45, vallejo,0.7685315239870832).
dist(vallejo, inst_del_petroleo,1.1780729045305436).
dist(inst_del_petroleo, lindavista,1.1783841768561658).
dist(lindavista, deportivo_18_de_marzo,0.904043595387883).
dist(deportivo_18_de_marzo,la_villa_basilica,0.9593664194679637).
dist(la_villa_basilica, martin_carrera,1.503490571839904).
dist(martin_carrera, el_rosario,10.630996450186425).

dist(el_rosario, tezozomoc,0.4474430308568442).
dist(azcapotzalco,tezozomoc,1.099248498394835).
dist(ferreria,azcapotzalco,1.3974049427093864).
dist(norte_45,ferreria,1.2309193592242682).
dist(vallejo,norte_45,0.7685315239870832).
dist(inst_del_petroleo,vallejo,1.1780729045305436).
dist(lindavista,inst_del_petroleo,1.1783841768561658).
dist(deportivo_18_de_marzo,lindavista,0.904043595387883).
dist(la_villa_basilica,deportivo_18_de_marzo,0.9593664194679637).
dist(el_rosario,martin_carrera,10.630996450186425).
dist(martin_carrera,la_villa_basilica,1.503490571839904).

%%%%%%%%%Linea 7%%%%%%%%%%%%%%%%%%
dist(el_rosario, aquiles_serdan,0.6248222142393554).
dist(aquiles_serdan, camarones,0.5705281171116064).
dist(camarones, refineria,0.17275498042501694).
dist(refineria, tacuba,0.24224516036108168).
dist(tacuba, san_joaquin,0.3775266767278773).
dist(san_joaquin, polanco,0.23518856233321883).
dist(polanco, auditorio,0.17819713956436734).
dist(auditorio, constituyentes,0.2553490576913737).
dist(constituyentes, tacubaya,0.4861411990469541).
dist(tacubaya, san_pedro_pinos,0.24538008519640395).
dist(san_pedro_pinos, san_antonio,0.1191062524775883).
dist(san_antonio, mixcoac,0.2078780333137712).
dist(mixcoac, barranca_del_muerto,0.39724627855312483).

dist(aquiles_serdan,el_rosario,0.6248222142393554).
dist(camarones,aquiles_serdan,0.5705281171116064).
dist(refineria,camarones,0.17275498042501694).
dist(tacuba,refineria,0.24224516036108168).
dist(san_joaquin,tacuba,0.3775266767278773).
dist(polanco,san_joaquin,0.23518856233321883).
dist(auditorio,polanco,0.17819713956436734).
dist(constituyentes,auditorio,0.2553490576913737).
dist(tacubaya,constituyentes,0.4861411990469541).
dist(san_pedro_pinos,tacubaya,0.24538008519640395).
dist(san_antonio,san_pedro_pinos,0.1191062524775883).
dist(mixcoac,san_antonio,0.2078780333137712).
dist(barranca_del_muerto,mixcoac,0.39724627855312483).

%%%%%%%%%Linea 8%%%%%%%%%%%%%%%%%%
dist(garibaldi, bellas_artes,0.2853055107811898).
dist(bellas_artes, san_juan_de_letran,0.09667729113658333).
dist(san_juan_de_letran,salto_del_agua,0.1068239921504372).
dist(salto_del_agua,doctores,0.1581823544597915).
dist(doctores,obrera,0.1686145944802455).
dist(obrera,chabacano,0.9455083815137869).
dist(chabacano,la_viga,1.0563558069287013).
dist(la_viga,santa_anita,0.5098431172517305).
dist(santa_anita,coyuya,0.9091403470160744).
dist(coyuya,iztacalco,0.0).
dist(iztacalco,apatlaco,0.554057253907579).
dist(apatlaco,aculco,0.19052997909763042).
dist(aculco,escuadron_201,0.2284556170790572).
dist(escuadron_201,atlalilco,0.9285055631191919).
dist(atlalilco,iztapalapa,0.8732876136069027).
dist(iztapalapa,cerro_de_la_estrella,0.8828019153680933).
dist(cerro_de_la_estrella,uami,1.2047356604854216).
dist(uami,const_de_1917,1.2054039116738404).

dist(bellas_artes,garibaldi,0.2853055107811898).
dist(san_juan_de_letran,bellas_artes,0.09667729113658333).
dist(salto_del_agua,san_juan_de_letran,0.1068239921504372).
dist(doctores,salto_del_agua,0.1581823544597915).
dist(obrera,doctores,0.1686145944802455).
dist(chabacano,obrera,0.9455083815137869).
dist(la_viga,chabacano,1.0563558069287013).
dist(santa_anita,la_viga,0.5098431172517305).
dist(coyuya,santa_anita,0.9091403470160744).
dist(iztacalco,coyuya,0.0).
dist(apatlaco,iztacalco,0.554057253907579).
dist(aculco,apatlaco,0.19052997909763042).
dist(escuadron_201,aculco,0.2284556170790572).
dist(atlalilco,escuadron_201,0.9285055631191919).
dist(iztapalapa,atlalilco,0.8732876136069027).
dist(cerro_de_la_estrella,iztapalapa,0.8828019153680933).
dist(uami,cerro_de_la_estrella,1.2047356604854216).
dist(const_de_1917,uami,1.2054039116738404).

%%%%%%%%%Linea 9%%%%%%%%%%%%%%%%%%
dist(tacubaya, patriotismo,0.9122190733272224).
dist(patriotismo, chilpancingo,1.158813571857132).
dist(chilpancingo,centro_medico,1.4139842901446256).
dist(centro_medico,lazaro_cardenas,1.2089435844345524).
dist(lazaro_cardenas,chabacano,1.0135029247523208).
dist(chabacano,jamaica,1.5100902474730022).
dist(jamaica,mixiuhca,1.0300603208255346).
dist(mixiuhca,velodromo,1.0921376152264644).
dist(velodromo,ciudad_deportiva,1.3162786859483575).
dist(ciudad_deportiva,puebla,0.9778001558018082).
dist(puebla,pantitlan,1.153618745875906).

dist( patriotismo,tacubaya,0.9122190733272224).
dist( chilpancingo,patriotismo,1.158813571857132).
dist(centro_medico,chilpancingo,1.4139842901446256).
dist(lazaro_cardenas,centro_medico,1.2089435844345524).
dist(chabacano,lazaro_cardenas,1.0135029247523208).
dist(jamaica,chabacano,1.5100902474730022).
dist(mixiuhca,jamaica,1.0300603208255346).
dist(velodromo,mixiuhca,1.0921376152264644).
dist(ciudad_deportiva,velodromo,1.3162786859483575).
dist(puebla,ciudad_deportiva,0.9778001558018082).
dist(pantitlan,puebla,1.153618745875906).

%%%%%%%%%Linea 10%%%%%%%%%%%%%%%%%%
dist(pantitlan,agricola_oriental,0.308965809551487).
dist(agricola_oriental,canal_de_san_juan,1.1757578918741238).
dist(canal_de_san_juan,tepalcates,1.4483361523032046).
dist(tepalcates,guelatao,1.1876068548453818).
dist(guelatao,penon_viejo,2.082409785015124).
dist(penon_viejo,acatitla,1.2536456972466954).
dist(acatitla,santa_marta,1.2283624075711197).
dist(santa_marta,los_reyes,1.9958874162811537).

dist(los_reyes,la_paz,1.8015058183606891).
dist(agricola_oriental,pantitlan,0.308965809551487).
dist(canal_de_san_juan,agricola_oriental,1.1757578918741238).
dist(tepalcates,canal_de_san_juan,1.4483361523032046).
dist(guelatao,tepalcates,1.1876068548453818).
dist(penon_viejo,guelatao,2.082409785015124).
dist(acatitla,penon_viejo,1.2536456972466954).
dist(santa_marta,acatitla,1.2283624075711197).
dist(los_reyes,santa_marta,1.9958874162811537).
dist(la_paz,los_reyes,1.8015058183606891).

%%%%%%%%%Linea 11%%%%%%%%%%%%%%%%%%
dist(buenavista,guerrero,0.8682688959088419).
dist(guerrero,garibaldi,0.6285299218827378).
dist(garibaldi,lagunilla,0.9336425494407654).
dist(lagunilla,tepito,0.8895840717699498).
dist(tepito,morelos,0.5661452360782615).
dist(morelos,san_lazaro,0.41149871332884047).
dist(san_lazaro,rcardo_flores_magon,1.2449628630891068).
dist(rcardo_flores_magon,romero_rubio,0.0).
dist(romero_rubio,oceania,1.8335529184471).
dist(oceania,deportivo_oceania,0.9014786166808332).
dist(deportivo_oceania,bosque_de_aragon,1.1157296251561706).
dist(bosque_de_aragon,villa_de_aragon,0.8344114525714442).
dist(villa_de_aragon,nezahualcoyotl,0.8210691270320976).
dist(nezahualcoyotl,impulsora,0.6635264554356443).
dist(impulsora,rio_de_los_remedios,0.2723256802634779).
dist(rio_de_los_remedios,muzquiz,0.5390853281780909).
dist(muzquiz,tecnologico,0.7135268081424211).
dist(tecnologico,olimpica,0.3048631378275267).

dist(olimpica,plaza_aragon,0.38759169161525353).
dist(plaza_aragon,ciudad_azteca,0.31005273900769714).
dist(guerrero,buenavista,0.8682688959088419).
dist(garibaldi,guerrero,0.6285299218827378).
dist(lagunilla,garibaldi,0.9336425494407654).
dist(tepito,lagunilla,0.8895840717699498).
dist(morelos,tepito,0.5661452360782615).
dist(san_lazaro,morelos,0.41149871332884047).
dist(rcardo_flores_magon,san_lazaro,1.2449628630891068).
dist(romero_rubio,rcardo_flores_magon,0.0).
dist(oceania,romero_rubio,1.8335529184471).
dist(deportivo_oceania,oceania,0.9014786166808332).
dist(bosque_de_aragon,deportivo_oceania,1.1157296251561706).
dist(villa_de_aragon,bosque_de_aragon,0.8344114525714442).
dist(nezahualcoyotl,villa_de_aragon,0.8210691270320976).
dist(impulsora,nezahualcoyotl,0.6635264554356443).
dist(rio_de_los_remedios,impulsora,0.2723256802634779).
dist(muzquiz,rio_de_los_remedios,0.5390853281780909).
dist(tecnologico,muzquiz,0.7135268081424211).
dist(olimpica,tecnologico,0.3048631378275267).
dist(plaza_aragon,olimpica,0.38759169161525353).
dist(ciudad_azteca, plaza_aragon,0.31005273900769714).

















