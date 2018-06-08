<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--表单-->
                <el-form :inline="true" :model="params" class="demo-form-inline">
                    <el-form-item label="名称">
                        <el-input v-model="params.productName" placeholder="名称"></el-input>
                    </el-form-item>
                    <el-form-item label="类型">
                        <el-select v-model="params.productType" placeholder="请选择">
                            <el-option value="" label="请选择"></el-option>
                            <el-option value="1" label="窗帘"></el-option>
                            <el-option value="2" label="橱柜"></el-option>
                            <el-option value="3" label="配件"></el-option>
                        </el-select>
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
                    <el-table-column prop="productName" label="名称" header-align="center" align="left" sortable="custom" min-width="120">
                    </el-table-column>
                    <el-table-column prop="type" label="类型" header-align="center" align="center" min-width="120">
                    </el-table-column>
                    <el-table-column prop="length" label="长度" header-align="center" align="center" min-width="150">
                    </el-table-column>
                    <el-table-column prop="width" label="宽度" header-align="center" align="center" min-width="150">
                    </el-table-column>
                    <el-table-column prop="property" label="属性" header-align="center" align="left" min-width="150">
                    </el-table-column>
                    <el-table-column prop="unitPrice" label="单价" header-align="center" align="right" :formatter="priceFormat" min-width="150">
                    </el-table-column>
                    <el-table-column prop="imgUrl" label="预览图" header-align="center" align="left" min-width="250">
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
                    <el-table-column prop="createTime" label="创建" header-align="center" align="center" sortable="custom" :formatter="dateFormat"
                        width="170">
                    </el-table-column>
                    <el-table-column label="操作" width="150" fixed="right">
                        <template slot-scope="scope">
                            <el-button type="primary" size="small" @click="detail(scope.$index, scope.row)">查看</el-button>
                            <el-button type="danger" size="small" @click="delete(scope.$index, scope.row)">删除</el-button>
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
            <el-form ref="form" :rules="rules" :model="form.model" label-width="80px">
                <el-form-item label="名称" prop="productName">
                    <el-input :disabled="!form.editable" v-model="form.model.productName"></el-input>
                </el-form-item>
                <el-form-item label="类型" prop="productType">
                    <el-select :disabled="!form.editable" v-model="form.model.productType" placeholder="请选择类型">
                        <el-option v-for="item in typeOptions" :key="item.value" :value="item.value" :label="item.label"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="长度" prop="length">
                    <el-input :disabled="!form.editable" v-model="form.model.length"></el-input>
                </el-form-item>
                <el-form-item label="宽度" prop="width">
                    <el-input :disabled="!form.editable" v-model="form.model.width"></el-input>
                </el-form-item>
                <el-form-item label="其他属性" prop="property">
                    <el-input :disabled="!form.editable" v-model="form.model.property"></el-input>
                </el-form-item>
                <el-form-item label="单价" prop="unitPrice">
                    <el-input :disabled="!form.editable" v-model="form.model.unitPrice"></el-input>
                </el-form-item>
                <el-form-item label="预览图" prop="imgUrl">
                    <el-input :disabled="!form.editable" v-model="form.model.imgUrl"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" :disabled="!form.editable" v-model="form.model.remark"></el-input>
                </el-form-item>

            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="edit" v-show="!form.editable">编辑</el-button>
                <el-button v-show="form.editable" :disabled="!form.editable" @click="dialogFormVisible = false">取消</el-button>
                <el-button v-show="form.editable" :disabled="!form.editable" @click="resetForm">重置</el-button>
                <el-button v-show="form.editable" :disabled="!form.editable" type="primary" @click="save">保存</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        data() {
            return {
                params: {
                    productName: '',
                    productType: '',
                    page: 1,
                    pageSize: 5,
                    sort: "updateTime",
                    order: "desc"
                },
                tableData: [],
                dialogFormVisible: false,
                editLoading: false,
                form: {
                    model: {},
                    editable: false
                },
                total: 0,
                table_index: 999,
                typeOptions: [{
                    value: 1,
                    label: '窗帘'
                }, {
                    value: 2,
                    label: '橱柜'
                }, {
                    value: 3,
                    label: '配件'
                }],
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
            priceFormat(row, column, cellValue, index) {
                if (cellValue === undefined) {
                    return '0.00'
                }
                return this.$Utils.priceFormat(cellValue)
            },
            getList(params) {
                this.$http.post('/product/list', params).then((response) => {
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
                    productName: '',
                    productType: '',
                    length: '',
                    width: '',
                    property: '',
                    unitPrice: '',
                    imgUrl: '',
                    remark: ''
                }
                this.searchUser()
            },
            save() {
                var self = this;
                this.$refs["form"].validate(function (valid) {
                    if (valid) {
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
                    }
                });
            },
            edit() {
                this.form.editable = true
            },
            deleteAll() {
                this.$message.error('还没做呢!')
            },
            delete(index, row) {
                this.tableData.splice(index, 1)
                this.$message({
                    message: '操作成功！',
                    type: 'success'
                })
            },
            detail(index, row) {
                this.dialogFormVisible = true
                this.form = {
                    model: Object.assign({}, row),
                    editable: false
                }
                this.table_index = index
                this.title = '编辑商品信息'
            },
            resetForm() {
                this.form = {
                    model: {},
                    editable: false
                }
            },
            add() {
                this.dialogFormVisible = true
                this.title = '新增产品'
				this.form.editable = true
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
