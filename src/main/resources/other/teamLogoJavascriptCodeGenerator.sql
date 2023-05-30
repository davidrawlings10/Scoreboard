select concat(concat(concat(concat('import ',concat(REPLACE(t.name, ' ', ''),'Logo')), ' from "./teamLogos/nfl/'), REPLACE(t.name, ' ', ''),'Logo'), '.svg";') from team t where league = 'NFL';
select concat(concat(concat(concat('case ',t.id), ': '), 'return '), concat(REPLACE(t.name, ' ', ''),'Logo;')) from team t WHERE league = 'NFL';
