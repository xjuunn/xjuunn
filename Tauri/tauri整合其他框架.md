[toc]

# 整合Nuxt (AI生成)

使用 Nuxt 3 和 Tauri 2 开发跨平台桌面应用程序是一种强大的组合，既利用了 Nuxt 3 的前端框架特性，又结合了 Tauri 2 的桌面应用开发能力。以下是一个详细的指南，帮助你设置和开发使用 Nuxt 3 和 Tauri 2 的项目。

### 先决条件

1. 确保已安装 Node.js（推荐使用 LTS 版本）。
2. 确保已安装 Rust 和 Cargo。
3. 确保已安装 Tauri CLI。

### 步骤 1: 创建 Nuxt 3 项目

首先，创建一个新的 Nuxt 3 项目。

```sh
npx nuxi init nuxt3-tauri
cd nuxt3-tauri
npm install
```

### 步骤 2: 安装 Tauri

在 Nuxt 3 项目中安装 Tauri 依赖项。

```sh
npm install @tauri-apps/cli @tauri-apps/api
```

### 步骤 3: 初始化 Tauri 项目

初始化 Tauri 项目，这将在你的 Nuxt 3 项目中添加必要的 Tauri 配置文件和目录。

```sh
npx tauri init
```

在初始化过程中，你会被提示一些问题，比如应用名称、窗口大小等。你可以根据需要进行配置。

### 步骤 4: 配置 Tauri

编辑 `tauri.conf.json` 文件，使其适应 Nuxt 3 项目的结构。确保 `distDir` 指向 Nuxt 生成的静态文件目录（默认为 `.output/public`）。

```json
{
  "build": {
    "beforeBuildCommand": "npm run build",
    "beforeDevCommand": "npm run dev",
    "distDir": ".output/public",
    "devPath": "http://localhost:3000"
  },
  "package": {
    "productName": "nuxt3-tauri",
    "version": "0.1.0"
  },
  "tauri": {
    "windows": [
      {
        "title": "Nuxt 3 + Tauri",
        "width": 800,
        "height": 600
      }
    ],
    "security": {
      "csp": null
    }
  }
}
```

### 步骤 5: 修改 Nuxt 配置

编辑 `nuxt.config.ts` 文件，使 Nuxt 3 在生产模式下生成静态文件到 `.output/public` 目录。

```ts
export default defineNuxtConfig({
  ssr:false,
  routeRules: {
    '/': { prerender: true }
},
})

```

### 步骤 6: 运行开发环境

在开发环境中，你可以同时运行 Nuxt 和 Tauri。

1. 启动 Nuxt 开发服务器：

    ```sh
    npm run dev
    ```

2. 启动 Tauri 开发服务器：

    ```sh
    npx tauri dev
    ```

### 步骤 7: 构建生产环境

构建 Nuxt 3 和 Tauri 应用以生成生产版本。

1. 构建 Nuxt 3 项目：

    ```sh
    npm run build
    ```

2. 构建 Tauri 应用：

    ```sh
    npx tauri build
    ```

### 额外步骤：使用 Tauri API

可以在 Nuxt 3 中使用 Tauri 提供的 API 进行桌面功能开发。例如，使用 `@tauri-apps/api` 来访问文件系统、剪贴板等功能。

在你的 Nuxt 组件或页面中引入并使用 Tauri API：

```ts
import { readTextFile, writeTextFile } from '@tauri-apps/api/fs';

export default {
  async mounted() {
    // 读取文件
    const content = await readTextFile('path/to/file.txt');
    console.log(content);

    // 写入文件
    await writeTextFile('path/to/file.txt', 'Hello, Tauri!');
  }
}
```

### 完整的文件结构

最终，你的项目结构可能如下：

```
nuxt3-tauri/
├── .output/
├── src-tauri/
│   ├── icons/
│   ├── src/
│   ├── tauri.conf.json
│   └── ...
├── pages/
├── components/
├── nuxt.config.ts
├── package.json
├── ...
```

