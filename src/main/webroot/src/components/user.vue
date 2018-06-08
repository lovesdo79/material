<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--表单-->
                <el-form :inline="true" :model="params" class="demo-form-inline">
                    <el-form-item label="姓名">
                        <el-input v-model="params.name" placeholder="姓名/登录名"></el-input>
                    </el-form-item>

                    <el-button type="primary" @click="searchUser">查询</el-button>
                    <el-button type="primary" @click="add">新增</el-button>
                </el-form>

                <!--表格-->
                <el-table :data="tableData" border>
                    <el-table-column header-align="center" align="center" type="selection">
                    </el-table-column>
                    <el-table-column prop="userFullName" header-align="center" align="left" label="姓名" width="150">
                    </el-table-column>
                    <el-table-column prop="userName" header-align="center" align="left" label="登录名" width="150">
                    </el-table-column>
                    <el-table-column label="操作" header-align="center" align="center" width="200">
                        <template slot-scope="scope">
                            <!--<el-button type="primary" size="small" @click="userDetail(scope.$index, scope.row)">查看</el-button>-->
                            <el-button type="primary" size="small" @click="resetPasswd(scope.$index, scope.row)">重置密码</el-button>
                            <el-button type="danger" size="small" @click="deleteUser(scope.$index, scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="block">
                    <el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="params.page" :page-sizes="[5, 10, 30, 50, 100, 300, 500]"
                        :page-size="params.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
                    </el-pagination>
                </div>
            </el-col>
        </el-row>

        <el-dialog :title="formTitle" :visible.sync="dialogFormVisible" width="500px">
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="姓名" prop="userFullName">
                    <el-input :disabled="!formEditable" v-model="form.userFullName" placeholder="请输入姓名" />
                </el-form-item>
                <el-form-item label="登录名" prop="userName">
                    <el-input :disabled="!formEditable" v-model="form.userName" placeholder="请输入登录名" />
                </el-form-item>
                <el-form-item label="密码" prop="passwd">
                    <el-input type="password" width="60%" :disabled="!formEditable" v-model="form.passwd" placeholder="请输入密码，密码为空则为默认密码" />
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button v-show="!formEditable" @click="formEditable = true">编辑</el-button>
                <el-button v-show="formEditable" @click="dialogFormVisible = false">取消</el-button>
                <el-button v-show="formEditable" @click="resetForm">重置</el-button>
                <el-button v-show="formEditable" type="primary" @click="save">保存</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        data() {
            return {
                params: {
                    name: '',
                    page: 1,
                    pageSize: 5
                },
                tableData: [],
                dialogFormVisible: false,
                editLoading: false,
                formTitle: "新增",
                form: {
                    userName: '',
                    userFullName: '',
                    passwd: '',
                },
                formEditable: false,
                total: 500,
                table_index: 999,
                rules: {
                    userFullName: [{
                            required: true,
                            message: '请输入姓名',
                            trigger: 'blur'
                        },
                        {
                            min: 2,
                            max: 5,
                            message: '长度在 2 到 5 个字符',
                            trigger: 'blur'
                        }
                    ],
                    userName: [{
                            required: true,
                            message: '请输入登录名',
                            trigger: 'blur'
                        },
                        {
                            min: 3,
                            max: 15,
                            message: '长度在 3 到 5 个字符',
                            trigger: 'blur'
                        }
                    ],
                    passwd: [{
                        min: 3,
                        max: 15,
                        message: '长度在 3 到 15 个字符',
                        trigger: 'blur'
                    }]
                }
            };
        },
        created() {
            var p = JSON.parse(JSON.stringify(this.params));
            this.getList(p)
        },
        methods: {
            dateFormat(row, column) {
                var date = row['birthday'];
                if (date === undefined) {
                    return "";
                }
                return this.$dateFormat(date);
            },
            searchUser() {
                var p = JSON.parse(JSON.stringify(this.params));
                this.getList(p)
            },
            resetForm() {
                this.form = {
                    userName: '',
                    userFullName: '',
                    passwd: '',
                }
            },
            add() {
                this.formTitle = "新增用户"
                this.dialogFormVisible = true;
                this.formEditable = true
                this.$refs['form'].resetFields()
                this.resetForm();
            },
            save() {
                var self = this;
                this.$refs["form"].validate(function (valid) {
                    if (valid) {
                        if (self.form.passwd === "") {
                            self.$confirm("密码为空时，会使用默认密码，是否继续", '提示', {
                                confirmButtonText: '确定',
                                cancelButtonText: '取消',
                                type: 'warning'
                            }).then(() => {
                                var form = JSON.parse(JSON.stringify(self.form));
                                self.$http.post("/user/save", form).then((response) => {
                                    if (response.data.rtnCode === '00') {
                                        self.$message({
                                            message: "操作成功！",
                                            type: 'success'
                                        });
                                        self.resetForm();
                                        self.dialogFormVisible = false;
                                        self.searchUser();
                                    } else {
                                        self.$message({
                                            message: response.data.rtnMsg,
                                            type: 'error'
                                        });
                                    }
                                });
                            }).catch(() => {
                                self.$message({
                                    type: 'info',
                                    message: '已取消'
                                });
                            });
                        }
                    }
                });
            },
            getList(p) {
                this.$http.post('/user/list', p).then((response) => {
                    var pageUser = response.data;
                    this.total = pageUser.total;
                    this.tableData = pageUser.rows;
                });
            },
            deleteUser(index, row) {
                this.tableData.splice(index, 1);
                this.$message({
                    message: "操作成功！",
                    type: 'success'
                });
            },
            resetPasswd(index, row) {
                this.$confirm("您重置用户[" + row.userFullName + "/" + row.userName + "]的密码,此操作不可恢复,请确认", '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$http.post("/user/reset/" + row.userId, null).then((response) => {
                        if (response.data.rtnCode === '00') {
                            this.$message({
                                message: "操作成功！",
                                type: 'success'
                            });
                            this.searchUser();
                        } else {
                            this.$message({
                                message: response.data.rtnMsg,
                                type: 'error'
                            });
                        }
                    });

                });
            },
            userDetail(index, row) {
                this.dialogFormVisible = true;
                this.form = Object.assign({}, row);
                this.formEditable = false
                this.table_index = index;
            },
            sizeChange(val) {
                this.params.pageSize = val;
                this.searchUser();
            },
            currentChange(val) {
                this.params.page = val;
                this.searchUser();
            }
        }
    };
</script>
<style>
    .el-pagination {
        text-align: center;
        margin-top: 30px;
    }

    .el-message-box__btns .cancel {
        float: right;
        margin-left: 10px;
    }
</style>
