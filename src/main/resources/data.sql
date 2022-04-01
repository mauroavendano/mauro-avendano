insert  into persona (id, username, nombre, apellido, phone_number) values
	('1', 'mauroave', 'Mauro', 'Avendaño', '2645001007'),
	('2', 'lspinetta', 'Luis Alberto', 'Spinetta', '2645221332'),
	('3', 'rmollo', 'Ricardo', 'Mollo', '2644123123')
ON CONFLICT DO NOTHING;