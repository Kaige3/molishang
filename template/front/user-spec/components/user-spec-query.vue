<script lang="ts" setup>
  import { toRefs } from 'vue'
  import { useUserSpecStore } from '../store/user-spec-store'
  import { storeToRefs } from 'pinia'
  import DictSelect from '@/components/dict/dict-select.vue'
  import DatetimePicker from '@/components/datetime/datetime-picker.vue'
  import { DictConstants } from '@/apis/__generated/model/enums/DictConstants'

  const userSpecStore = useUserSpecStore()
  const { queryData } = storeToRefs(userSpecStore)
  const { query } = toRefs(queryData.value)
</script>
<template>
  <div class="search">
    <el-form inline label-width="80" size="small">
    <el-form-item label="手机号">
                <el-input v-model="query.getPhone"></el-input>
    </el-form-item>
    <el-form-item label="密码">
                <el-input v-model="query.getPassword"></el-input>
    </el-form-item>
    <el-form-item label="昵称">
                <el-input v-model="query.getNickname"></el-input>
    </el-form-item>
    <el-form-item label="性别">
                <dict-select :dict-id="DictConstants.SEX" v-model="query.getGender"></dict-select>
    </el-form-item>
      <el-form-item label=" ">
        <div class="btn-wrapper">
          <el-button
                  type="primary"
                  size="small"
                  @click="userSpecStore.reloadTableData({ query: query, likeMode: 'ANYWHERE' })"
          >
            查询
          </el-button>
          <el-button type="warning" size="small" @click="userSpecStore.restQuery()"> 重置</el-button>
        </div>
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
  :deep(.el-form-item) {
    margin-bottom: 5px;
  }

  .search {
    display: flex;
    flex-flow: column nowrap;
    width: 100%;

    .btn-wrapper {
      margin-left: 20px;
    }
  }
</style>

