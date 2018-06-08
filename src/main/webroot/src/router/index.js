import Vue from 'vue'
import Router from 'vue-router'
import Index from '../components/index.vue'
import Login from '../components/login.vue'
import Customer from '../components/customer.vue'
import Order from '../components/order.vue'
import User from '../components/user.vue'
import Supplier from '../components/supplier.vue'
import Product from '../components/product.vue'

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
        name: '登录'
    }, {
        path: '/index',
        component: Index,
        name: '首页',
        children: [{
            path: '/customer',
            component: Customer,
            name: '客户管理'
        }, {
            path: '/order',
            component: Order,
            name: '订单管理'
        }, {
            path: '/user',
            component: User,
            name: '用户管理'
        },{
            path: '/supplier',
            component: Supplier,
            name: '供货商管理'
        }, {
            path: '/product',
            component: Product,
            name: '商品管理'
        }]
    }]
})
