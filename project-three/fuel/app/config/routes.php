<?php
return array(
	'_root_'  => 'main/index',
	'_404_'   => 'main/404',

	'/'  => array('main/index', 'name' => 'home'),
	'items/:id' => array('items/details', 'name' => 'item_details'),
	'login' => array('users/login', 'name' => 'login'),
);