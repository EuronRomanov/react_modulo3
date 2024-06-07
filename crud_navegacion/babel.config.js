module.exports = function(api) {
  api.cache(true);
  return {
    presets: ['babel-preset-expo'],
    plugin:[
      [
      "module-resolver",
      {
        extensions:['.tsx','.ts','.js','.json']
      }
    ],
    'react-native-reanimated/plugin'
    ]
  };
};