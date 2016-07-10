<?php
 /*
 Plugin Name: Kofun Counter
 Description: Add-on for Mavis project in form of plugin for Wordpress, that count posts from given time.
 Version: 0.1
 Author: Zegis
 Author: http://kofun.pl
 */

if( ! class_exists( 'Kofun_Counter') ) {
	 
	class Kofun_Counter {
		public function __construct(){
			add_filter( 'xmlrpc_methods', array( &$this, 'add_count_xmlrpc_methods') );
		}
		
		public function add_count_xmlrpc_methods($methods){
			$methods['posts.countByMonth'] = array( &$this, 'count_by_month' );
			return $methods;			
		}
		
		function count_by_month ($params){
		
			global $wp_xmlrpc_server;
			
			$blog_id = $params[0];
			$username = $params[1];
			$password = $params[2];
			
			$monthnum = $params[3];
			
			return 0;
		
		}
	}
		 
	$GLOBALS['Kofun_Counter'] = new Kofun_Counter();
}
?>