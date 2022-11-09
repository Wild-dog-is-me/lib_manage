<template>
    <div style="width: 80%">
      <div style="margin-bottom: 30px">新增管理员</div>
      <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="姓名" prop="username">
          <el-input v-model="form.username" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
      </el-form>

      <div style="text-align: center; margin-top: 30px">
        <el-button type="primary" @click="save" size="medium">提交</el-button>
      </div>
    </div>
  </template>

  <script>
  import request from "@/api/request";

  export default {
    name: 'AddUser',
    data() {
      const checkPhone = (rule, value, callback) => {
        if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
          callback(new Error('请输入合法的手机号'));
        }
        callback()
      };
      const validateEmail = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请正确填写邮箱'));
        } else {
          if (value !== '') {
            var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
            if(!reg.test(value)){
              callback(new Error('请输入有效的邮箱'));
            }
          }
          callback();
        }
      };
      return {
        form: {},
        rules: {
          username: [
            { required: true, message: '请输入姓名', trigger: 'blur'}
          ],
          phone: [
            { validator: checkPhone, trigger: 'blur' }
          ],
          email: [
            { validator: validateEmail, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      save() {
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            request.post('/admin/save', this.form).then(res => {
              if (res.code === '200') {
                this.$notify.success('新增成功')
                this.$refs['ruleForm'].resetFields()
                this.$router.push("/adminList")
              } else {
                this.$notify.error(res.msg)
              }
            })
          }
        })
      },
      resetFields(){
        this.form = {}
      }
    }
  }
</script>
