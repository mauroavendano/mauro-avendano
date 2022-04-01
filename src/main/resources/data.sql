insert  into persona (id, username, nombre, apellido, phone_number) values
	('1', 'mauroave', 'Mauro', 'Avendaño', '2645001007'),
	('2', 'lspinetta', 'Luis Alberto', 'Spinetta', '2645221332'),
	('3', 'rmollo', 'Ricardo', 'Mollo', '2644123123'),
	('4', 'jchire', 'Juan', 'Chire', '1234567'),
	('5', 'mferrari', 'Matias', 'Ferrari', '12345678'),
	('6', 'gcastro', 'Gonzalo', 'Castro', '123444'),
	('7', 'esanchez', 'Emiliano', 'Sanchez', '11234443'),
	('8', 'ssalvo', 'Santiago', 'Salvo', '547898'),
	('9', 'ilopez', 'Ignacio Esteban', 'Lopez Balaguer', '123123123'),
	('10', 'lmascotti', 'Luciano', 'Mascotti', '23241242'),
	('11', 'forellano', 'Federico', 'Orellano', '114598'),
	('12', 'fgarrido', 'Flavia', 'Garrido', '162512')
ON CONFLICT DO NOTHING;

insert  into contacto (id, me_id, contact_id) values
	('1', 1, 2),
	('2', 1, 3),
	('3', 1, 4),
	('4', 1, 5)
ON CONFLICT DO NOTHING;

insert  into grupo (id, nombre) values
	('1', 'Amigos de la secundaria'),
	('2', 'Trabajo'),
	('3', 'Chicos del barrio')
ON CONFLICT DO NOTHING;

insert  into grupo_persona (id, persona_id, grupo_id) values
	('1', 1, 1),
	('2', 10, 1),
    ('3', 11, 1),
    ('4', 12, 1),
    ('5', 1, 2),
    ('6', 4, 2),
    ('7', 5, 2),
    ('8', 6, 2),
    ('9', 1, 3),
    ('10', 7, 3),
    ('11', 8, 3),
    ('12', 9, 3)
ON CONFLICT DO NOTHING;