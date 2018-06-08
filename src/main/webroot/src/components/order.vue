<template>
    <section>
        <el-row>
            <el-col :span="24">
                <!--表单-->
                <el-form :inline="true" :model="params" class="demo-form-inline">
                    <el-form-item label="客户姓名">
                        <el-input v-model="params.name" placeholder="客户姓名"></el-input>
                    </el-form-item>
                    <el-form-item label="电话">
                        <el-input v-model="params.tel" placeholder="客户电话"></el-input>
                    </el-form-item>
                    <el-form-item label="商品">
                        <el-input v-model="params.product" placeholder="产品名称"></el-input>
                    </el-form-item>
                    <el-form-item label="地址">
                        <el-input v-model="params.address" placeholder="地址"></el-input>
                    </el-form-item>
                    <el-button type="primary" icon="search" @click="searchUser">查询</el-button>
                    <el-button type="primary" @click="reset">重置</el-button>
                </el-form>

                <!--表格-->
                <el-table :data="tableData" border default-sort="{prop:'updateTime',order:'descending'}" @sort-change="sortChange" style="width: 100%">
                    <el-table-column header-align="center" align="center" type="selection">
                    </el-table-column>
                    <el-table-column prop="orderNo" header-align="center" align="center" label="订单编号" width="150">
                    </el-table-column>
                    <el-table-column prop="name" header-align="center" align="left" label="客户姓名" sortable="custom" width="150">
                    </el-table-column>
                    <el-table-column prop="tel" header-align="center" align="left" label="电话" width="150">
                    </el-table-column>
                    <el-table-column prop="installationDate" header-align="center" align="center" label="安装日期" :formatter="dateFormat" width="120">
                    </el-table-column>
                    <el-table-column prop="address" header-align="center" align="left" label="地址" width="150">
                    </el-table-column>
                    <el-table-column prop="prodNames" header-align="center" align="left" label="商品" width="150">
                    </el-table-column>
                    <el-table-column prop="totalPrice" header-align="center" align="right" label="订单总金额" :formatter="priceFormat" width="150">
                    </el-table-column>
                    <el-table-column prop="updateTime" header-align="center" align="center" label="更新时间" sortable="custom" :formatter="timeFormat"
                        width="170">
                    </el-table-column>
                    <el-table-column prop="createTime" header-align="center" align="center" label="创建时间" sortable="custom" :formatter="timeFormat"
                        width="170">
                    </el-table-column>
                    <el-table-column width="150" align="center" fixed="right" label="操作">
                        <template slot-scope="scope">
                            <el-button type="primary" size="small" @click="userDetail(scope.$index, scope.row)">查看
                            </el-button>
                            <el-button type="danger" size="small" @click="deleteUser(scope.$index, scope.row)">删除
                            </el-button>
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

        <el-dialog title="修改个人信息" :visible.sync="dialogFormVisible" size="tiny">
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item label="出生日期">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.birthday" style="width: 100%;"></el-date-picker>
                </el-form-item>
            </el-form>
        </el-dialog>

    </section>
</template>

<script>
    export default {
        data() {
            return {
                params: {
                    name: '',
                    product: '',
                    address: '',
                    tel: '',
                    page: 1,
                    pageSize: 5,
                    sort: "updateTime",
                    order: "desc"
                },
                tableData: [],
                dialogFormVisible: false,
                editLoading: false,
                form: {
                    userName: '',
                    address: '',
                    birthday: '',
                },
                total: 0,
                table_index: 999,
            };
        },
        created() {
            var p = JSON.parse(JSON.stringify(this.params));
            this.getList(p);
        },
        methods: {
            dateFormat(row, column, cellValue, index) {
                if (cellValue === undefined) {
                    return ''
                }
                return this.$Utils.dateFormat(cellValue)
            },
            timeFormat(row, column, cellValue, index) {
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
            reset() {
                this.params = {
                    name: "",
                    tel: "",
                    product: "",
                    address: ""
                }
                this.searchUser()
            },
            sortChange(sort) {
                this.params.sort = sort.prop;
                this.params.order = sort.order === "ascending" ? "asc" : "desc";

                this.getList(this.params);
            },
            searchUser() {
                var p = JSON.parse(JSON.stringify(this.params));
                this.getList(p);
            },
            getList(p) {
                this.$http.post('/order/list', p).then((response) => {
                    var pageUser = response.data;
                    this.total = pageUser.total;
                    // this.params.page = pageUser.page;
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
            userDetail(index, row) {
                // this.$router.push("/index/customer");
                this.dialogFormVisible = true;
                this.form = Object.assign({}, row);
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
