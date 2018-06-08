<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--表单-->
                <el-form :inline="true" :model="params" class="demo-form-inline">
                    <el-form-item label="姓名">
                        <el-input v-model="params.name" placeholder="姓名"></el-input>
                    </el-form-item>
                    <el-form-item label="电话">
                        <el-input v-model="params.tel" placeholder="电话"></el-input>
                    </el-form-item>
                    <el-form-item label="地址">
                        <el-input v-model="params.address" placeholder="地址"></el-input>
                    </el-form-item>
                    <el-button type="primary" icon="search" @click="searchUser">查询</el-button>
                    <el-button type="primary" @click="reset">重置</el-button>
                    <el-button type="primary" icon="delete" @click="deleteAll">批量删除</el-button>
                    <el-button type="primary" icon="add" @click="add">新增</el-button>
                </el-form>

                <!--表格-->
                <el-table :data="tableData" border :default-sort="{prop:'updateTime',order:'descending'}" @sort-change="sortChange" style="width: 100%">
                    <el-table-column header-align="center" align="center" type="selection">
                    </el-table-column>
                    <el-table-column prop="name" label="客户姓名" header-align="center" align="left" sortable="custom" min-width="120">
                    </el-table-column>
                    <el-table-column prop="tel" label="电话" header-align="center" align="left" min-width="120">
                    </el-table-column>
                    <el-table-column prop="customerType" label="类别" header-align="center" align="center" min-width="150">
                    </el-table-column>
                    <el-table-column prop="address" label="地址" header-align="center" align="left" min-width="250">
                    </el-table-column>
                    <el-table-column label="备注" header-align="center" align="left" min-width="250">
                        <template slot-scope="scope">
                            <el-popover trigger="hover" width="500" placement="top">
                                <p>{{ scope.row.remark }}</p>
                                <div slot="reference" class="name-wrapper">
                                    {{ scope.row.remark | filterRemark }}
                                </div>
                            </el-popover>
                        </template>
                    </el-table-column>
                    <el-table-column prop="updateTime" label="更新时间" header-align="center" align="center" sortable="custom" :formatter="dateFormat"
                        width="170">
                    </el-table-column>
                    <el-table-column label="操作" width="150" fixed="right">
                        <template slot-scope="scope">
                            <el-button type="primary" size="small" @click="userDetail(scope.$index, scope.row)">查看</el-button>
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

        <el-dialog :title="title" :visible.sync="dialogFormVisible" size="tiny">
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="姓名" prop="name">
                    <el-input :disabled="!formEditable" v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="tel">
                    <el-input :disabled="!formEditable" v-model="form.tel"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                    <el-input :disabled="!formEditable" v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" :disabled="!formEditable" v-model="form.remark"></el-input>
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
                    tel: '',
                    address: '',
                    types: "3",
                    page: 1,
                    pageSize: 5,
                    sort: "updateTime",
                    order: "desc"
                },
                tableData: [],
                dialogFormVisible: false,
                editLoading: false,
                form: {},
                formEditable: false,
                total: 0,
                table_index: 999,
                title: '新增客户',
                rules: {
                    name: [{
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
                    tel: [{
                            required: true,
                            message: '请输入电话号码',
                            trigger: 'blur'
                        },
                        {
                            min: 7,
                            max: 25,
                            message: '长度在 7 到 25 个字符',
                            trigger: 'blur'
                        }
                    ],
                    address: [{
                        min: 2,
                        max: 200,
                        message: '长度在 7 到 200 个字符',
                        trigger: 'blur'
                    }],
                    type: [{
                        required: true,
                        message: '请选择客户类型',
                        trigger: 'blur'
                    }]
                }
            }
        },
        created() {
            var p = JSON.parse(JSON.stringify(this.params))
            this.getList(p)
        },
        // 		computed:{
        // 			filterRemark:function(){
        // 				if()
        // 			}
        // 		},
        methods: {
            dateFormat(row, column, cellValue, index) {
                if (cellValue === undefined) {
                    return ''
                }
                return this.$Utils.timeFormat(cellValue)
            },
            getList(params) {
                this.$http.post('/customer/list', params).then((response) => {
                    var pageUser = response.data
                    this.total = pageUser.total
                    // this.params.page = pageUser.total / this.params.pageSize
                    this.tableData = pageUser.rows
                })
            },
            searchUser() {
                var p = JSON.parse(JSON.stringify(this.params))
                this.getList(p)
            },
            sortChange(sort) {
                this.params.sort = sort.prop;
                this.params.order = sort.order === "ascending" ? "asc" : "desc";

                this.getList(this.params);
            },
            reset() {
                this.params = {
                    name: '',
                    tel: '',
                    address: '',
                    page: 1,
                    pageSize: 5,
                    sort: "updateTime",
                    order: "desc"
                }
                this.searchUser()
            },
            save() {
                var self = this;
                this.$refs["form"].validate(function (valid) {
                    if (valid) {
                        var form = JSON.parse(JSON.stringify(self.form));
                        self.$http.post("/customer/save", form).then((response) => {
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
                    }
                });
            },
            deleteAll() {
                this.$message.error('还没做呢!')
            },
            deleteUser(index, row) {
                this.$confirm("您将删除供应商[" + row.name + "],此操作不可恢复,请确认", '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$http.post("/customer/del/" + row.customerId, null).then((response) => {
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
                this.dialogFormVisible = true
                this.form = Object.assign({}, row)
                this.formEditable = false
                this.table_index = index
                this.title = '客户信息'
            },
            resetForm() {
                this.form = {
                    name: '',
                    tel: '',
                    address: '',
                    type: '3',
                    remark: ''
                }
            },
            add() {
                this.dialogFormVisible = true
                this.title = '新增客户'
                this.formEditable = true
                this.$refs['form'].resetFields()
                this.resetForm();
            },
            sizeChange(val) {
                this.params.pageSize = val
                this.searchUser()
            },
            currentChange(val) {
                this.params.page = val
                this.searchUser()
            }
        },
        filters: {
            filterRemark: function (value) {
                if (!value) {
                    return "";
                } else if (value.length > 20) {
                    value = value.slice(0, 17) + "...";
                }
                return value;
            }
        }
    }
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
