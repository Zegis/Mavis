<?php
 /*
 Plugin Name: Kofun Counter
 Description: Add-on for Mavis project in form of plugin for Wordpress, that count posts from given time.
 Version: 0.3
 Author: Zegis
 Author: http://kofun.pl
 czyny id = 46
 */

if( ! class_exists( 'Kofun_Counter') ) {
	 
	class Kofun_Counter {
		public function __construct(){
			add_filter( 'xmlrpc_methods', array( &$this, 'add_count_xmlrpc_methods') );
		}
		
		public function add_count_xmlrpc_methods($methods){
			$methods['kofun.countForMonth'] = array( &$this, 'countPostsInMonth' );
			return $methods;			
		}
		
		function countPostsInMonth ($params){
		
			global $wp_xmlrpc_server;
			$args = array(
				'post_type'      => 'post',
				'year'           => $params[0],
				'monthnum'       => $params[1],
				'tag__not_in'    => '46'
			);
			
			$custom_query = new WP_Query($args);
			
			$ret = 0;
			
			if($custom_query->have_posts()){
				while($custom_query->have_posts()){
					$custom_query->the_post();
					$ret++;
				}
			}
			
			return $ret;
		}
	}
		 
	$GLOBALS['Kofun_Counter'] = new Kofun_Counter();
}
?>