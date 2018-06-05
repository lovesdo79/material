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
				<el-table :data="tableData" border style="width: 100%">
					<el-table-column type="selection">
					</el-table-column>
					<el-table-column prop="name" label="客户姓名" min-width="120">
					</el-table-column>
					<el-table-column prop="tel" label="电话" min-width="120">
					</el-table-column>
					<el-table-column prop="customerType" label="类别" min-width="150">
					</el-table-column>
					<el-table-column prop="address" label="地址" min-width="250">
					</el-table-column>
					<el-table-column label="备注" min-width="250">
						<template slot-scope="scope">
							<el-popover trigger="hover" width="500" placement="top">
								<p>{{ scope.row.remark }}</p>
								<div slot="reference" class="name-wrapper">
									{{ scope.row.remark | filterRemark }}
								</div>
							</el-popover>
						</template>
					</el-table-column>
					<el-table-column prop="updateTime" label="更新时间" sortable :formatter="dateFormat" width="180">
					</el-table-column>
					<el-table-column label="操作" width="150" fixed="right">
						<template slot-scope="scope">
							<el-button type="primary" size="small" @click="userDetail(scope.$index, scope.row)">查看</el-button>
							<el-button type="danger" size="small" @click="deleteUser(scope.$index, scope.row)">删除</el-button>
						</template>
					</el-table-column>
				</el-table>
				<div class="block">
					<el-pagination @size-change="sizeChange" @current-change="currentChange" :current-page="params.page" :page-sizes="[5, 10, 30, 50, 100, 300, 500]" :page-size="params.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
					</el-pagination>
				</div>
			</el-col>
		</el-row>

		<el-dialog :title="title" :visible.sync="dialogFormVisible" size="tiny">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="姓名">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="电话">
					<el-input v-model="form.tel"></el-input>
				</el-form-item>
				<el-form-item label="地址">
					<el-input v-model="form.address"></el-input>
				</el-form-item>
				<el-form-item label="客户类型">
					<el-select v-model="form.type" placeholder="请选择客户类型">
						<el-option v-for="item in customerTypeOptions" :key="item.value" :value="item.value" :label="item.label"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="备注">
					<el-input type="textarea" v-model="form.remark"></el-input>
				</el-form-item>

				<el-button type="primary" icon="search" @click="save">保存</el-button>
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
					tel: '',
					address: '',
					page: 1,
					pageSize: 5
				},
				tableData: [],
				dialogFormVisible: false,
				editLoading: false,
				form: {
					name: '',
					tel: '',
					address: '',
					type: '',
					remark: ''
				},
				total: 0,
				table_index: 999,
				customerTypeOptions: [{
					value: 1,
					label: '散客'
				}, {
					value: 2,
					label: '批发商'
				}, {
					value: 3,
					label: '供应商'
				}],
				title: '新客户'
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
				if(cellValue === undefined) {
					return ''
				}
				return this.$dateFormat(cellValue)
			},
			getList(p) {
				this.$http.post('/customer/list', p).then((response) => {
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
			reset() {
				this.params = {
					name: "",
					tel: "",
					address: ""
				}
				this.searchUser()
			},
			save() {
				this.$message({
					message: JSON.stringify(this.form),
					type: 'success'
				});
			},
			deleteAll() {
				this.$message.error('还没做呢!')
			},
			deleteUser(index, row) {
				this.tableData.splice(index, 1)
				this.$message({
					message: '操作成功！',
					type: 'success'
				})
			},
			userDetail(index, row) {
				this.dialogFormVisible = true
				this.form = Object.assign({}, row)
				this.table_index = index
				this.title = '编辑客户信息'
			},
			add() {
				this.dialogFormVisible = true
				this.title = '新增客户'
				this.form = {
					name: '',
					tel: '',
					address: '',
					type: '',
					remark: ''
				}
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
			filterRemark: function(value) {
				if(!value) {
					return "";
				} else if(value.length > 20) {
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
	
	.el-table--border th {
		text-align: center
	}
</style>