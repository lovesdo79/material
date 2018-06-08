<template>
    <div id='menu'>
        <el-menu :default-active="$route.path" class="mar-l el-menu-vertical-demo el-col el-col-3" light router>
            <el-menu-item-group title="琪琪窗帘">
                <template v-for="(item,index) in blog_item" v-if="!item.hidden">
                    <el-menu-item :index="item.path">
                        <i class="fa" :class="item.class"></i>{{item.title}}</el-menu-item>
                </template>
            </el-menu-item-group>
            <el-menu-item-group title="物料管理">
                <template v-for="(item,index) in element_item" v-if="!item.hidden">
                    <el-menu-item :index="item.path">
                        <i class="fa" :class="item.class"></i>{{item.title}}</el-menu-item>
                </template>
            </el-menu-item-group>
        </el-menu>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                element_item: [{
                    title: '供货商管理',
                    path: '/supplier',
                    class: 'fa-newspaper-o'
                }, {
                    title: '商品管理',
                    path: '/product',
                    class: 'fa-pagelines'
                }],
                blog_item: [{
                    title: '订单管理',
                    path: '/order',
                    class: 'fa-newspaper-o'
                }, {
                    title: '客户管理',
                    path: '/customer',
                    class: 'fa-users'
                }, {
                    title: '用户管理',
                    path: '/user',
                    class: 'fa-user-o'
                }]
            }
        }
    }
</script>
<style>
    i.fa {
        vertical-align: baseline;
        margin-right: 10px;
    }

    #menu .el-menu {
        height: 100%;
        background-color: #fff;
        position: fixed;
        float: left;
        border-right: solid 1px #ccc;
        overflow: auto;
    }
</style>
