<template>
  <div>
    <el-descriptions
      class="margin-top"
      title="管理员信息"
      :column="3"
      :size="size"
      direction="vertical"
      
      border
      :contentStyle="CS"
      :label-style="LS"
    >
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          用户名
        </template>
        <el-tag>{{ admin.username }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-mobile-phone"></i>
          手机号
        </template>
        <el-tag>{{ admin.phone }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-location-outline"></i>
          邮箱
        </template>
        <el-tag>{{ admin.email }}</el-tag>
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import request from "@/api/request";

export default {
  data() {
    return {
      admin: Cookies.get("admin") ? JSON.parse(Cookies.get("admin")) : {},
    };
  },
  created() {
    request.get("/admin/" + this.admin.id).then((res) => {
      this.admin = res.data;
    });
  },
};
</script>

<style>
.item {
  margin: 10px 0;
  color: #666;
}
</style>
