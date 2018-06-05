<template>
    <div id='app'>
        <div class="app-header">
            <div class="title">后台管理系统</div>
        </div>
        <div class="app-content">
            <div class="app-nav">
                <div class="app-nav-wrap">
                    <el-menu default-active="activeIndex" class="el-menu-vertical-demo" @select="handlerSelect" @open="handleOpen" @close="handleClose"
                        light router>
                        <el-menu-item v-for="menu in menus" :index="menu.path" :key="menu.path">
                            <i class="fa" :class="menu.class"></i>{{menu.title}}
                        </el-menu-item>
                    </el-menu>
                </div>
            </div>
            <div class="app-wrap">
                <div class="template-tabs">
                    <el-tabs v-model="activeIndex" type="card" closable @tab-click="tabClick" @tab-remove="removeTab">
                        <el-tab-pane v-for="(item, index) in editableTabs2" :key="item.path" :label="item.title" :name="item.path">
                            <!-- {{item.content}}-->

                        </el-tab-pane>
                    </el-tabs>
                </div>
                <div class="content-wrap">
                    <router-view/>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import header from './components/header/header.vue';
    import menu from './components/menu/menu.vue';
    export default {
        data() {
            return {
                menus: [{
                    title: '订单管理',
                    path: '/index/order',
                    class: 'fa-newspaper-o'
                }, {
                    title: '客户管理',
                    path: '/index/customer',
                    class: 'fa-user'
                }, {
                    title: '评论',
                    path: '/index/comment',
                    class: 'fa-comment-o'
                }],
                activeIndex: '/',
                editableTabs2: [{
                    title: "首页",
                    path: "/"
                }],
                tabIndex: 2
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            handlerSelect(key, keypath) {
                let tabs = this.editableTabs2;

                for (let tab of tabs) {
                    if (tab.path == key) {
                        this.activeIndex = key;
                        return;
                    }
                }
                let menus = this.menus;
                let tabName = "";
                menus.forEach((item, index) => {
                    if (item.path === key) {
                        tabName = item.title;
                    }
                });
                this.addTab(tabName, key);
            },
            addTab(tabName, path) {
                let newTab = path;
                this.editableTabs2.push({
                    title: tabName,
                    path: newTab
                    // content: 'New Tab content'
                });
                this.activeIndex = newTab;
            },
            removeTab(targetName) {
                if (targetName === "/") {
                    this.$message({
                        message: '首页不可删除',
                        type: 'warning',
                        showClose: true,
                        center: true
                    });
                    return;
                }
                let tabs = this.editableTabs2;
                let activeName = this.activeIndex;
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.path === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.path;
                            }
                        }
                    });
                }

                this.activeIndex = activeName;
                this.$router.push({
                    path: activeName
                });
                this.editableTabs2 = tabs.filter(tab => tab.path !== targetName);
            },
            tabClick(tab, action) {
                console.log("click taget" + tab.name)
                // this.default_active_index = tab.name
                let path = this.activeIndex;
                // 用户详情页的时候，对应了二级路由，需要拼接添加第二级路由
                if (this.activeIndex === '/userInfo') {
                    path = this.activeIndex + '/' + this.$store.state.userInfo.name;
                }
                this.$router.push({
                    path: path
                });
            }
        }
        //         mounted() {
        //             // 刷新时以当前路由做为tab加入tabs
        //             if (this.$route.path !== '/'&& this.$route.path!=="/index" && this.$route.path.indexOf('userInfo') == -1) {
        //                 this.$store.commit('add_tabs', {
        //                     route: '/',
        //                     name: '首页'
        //                 });
        //                 this.$store.commit('add_tabs', {
        //                     route: this.$route.path,
        //                     name: this.$route.name
        //                 });
        //                 this.$store.commit('set_active_index', this.$route.path);
        //             } else {
        //                 this.$store.commit('add_tabs', {
        //                     route: '/',
        //                     name: '首页'
        //                 });
        //                 this.$store.commit('set_active_index', '/');
        //                 this.$router.push('/');
        //             }
        // 
        //         },
        //         computed: {
        //             options() {
        //                 return this.$store.state.options;
        //             }
        //         }
    };
</script>
<style lang="scss">
    html,
    body {
        font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
        background-color: #fff;

        height: 100%;
        margin: 0;
        padding: 0;
    }


    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        height: 100%;
        display: flex;
        flex-flow: column;
        overflow: hidden;
        .app-header {
            color: #fff;
            flex: 0 0 60px;
            background: #324057;
            height: 60px;
            line-height: 60px;
            padding: 0 20px;
            .title {
                font-size: 24px;
            }
        }
        .app-content {
            flex: 1;
            display: flex;
            flex-flow: row;

            .app-nav {
                flex: 0 0 200px;
                background: #eff2f7;
            }
            .app-wrap {
                flex: 1;
                padding: 10px 20px;
                overflow: auto;
                .content-wrap {
                    height: 90%; //border: 1px solid #d1dbe5;
                    border-top: none;
                    padding: 0 20px;
                }
            }
        }
    }

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

    .container {
        padding-top: 80px;
        height: 100%;
    }

    .container .main {
        padding: 0;
    }

    .container ul li.el-menu-item {
        font-size: 100%;
    }

    .container .mar-l {
        padding: 0;
    }

    .container .content-wrapper {
        padding: 20px;
    }

    .el-row {
        margin-bottom: 20px;
        &:last-child {
            margin-bottom: 0;
        }
    }

    .el-col {
        border-radius: 4px;
    }

    .bg-purple-dark {
        background: #99a9bf;
    }

    .bg-purple {
        background: #d3dce6;
    }

    .bg-purple-light {
        background: #e5e9f2;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }
</style>
