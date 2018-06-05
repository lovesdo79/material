import Vue from 'vue'
import Router from 'vue-router'
import Index from '../components/index.vue'
import Login from '../components/login.vue'
import Customer from '../components/blog/customer.vue'
import Order from '../components/blog/order.vue'
import Comment from '../components/blog/comment.vue'

Vue.use(Router)

export default new Router({
	mode: 'hash',
	linkActiveClass: 'active',
	routes: [{
		path: '/',
		redirect: 'index'
	}, {
		path: '/login',
		component: Login,
		name: 'login'
	}, {
		path: '/index',
		component: Index,
		name: 'index'
	}, {
		path: '/index/customer',
		component: Customer,
		name: 'customer'
	}, {
		path: '/index/order',
		component: Order,
		name: 'order'
	}, {
		path: '/index/comment',
		component: Comment,
		name: 'comment'
	}]
})