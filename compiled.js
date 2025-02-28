"use strict";

function _typeof(o) { "@babel/helpers - typeof"; return _typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (o) { return typeof o; } : function (o) { return o && "function" == typeof Symbol && o.constructor === Symbol && o !== Symbol.prototype ? "symbol" : typeof o; }, _typeof(o); }
Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = Login;
var _react = _interopRequireDefault(require("react"));
var _reactNative = require("react-native");
var _logo = _interopRequireDefault(require("../../images/logo2.png"));
var _Ionicons = _interopRequireDefault(require("react-native-vector-icons/Ionicons"));
var _native = require("@react-navigation/native");
var _formik = require("formik");
var yup = _interopRequireWildcard(require("yup"));
var _authContext = require("../auth/authContext");
function _getRequireWildcardCache(e) { if ("function" != typeof WeakMap) return null; var r = new WeakMap(), t = new WeakMap(); return (_getRequireWildcardCache = function _getRequireWildcardCache(e) { return e ? t : r; })(e); }
function _interopRequireWildcard(e, r) { if (!r && e && e.__esModule) return e; if (null === e || "object" != _typeof(e) && "function" != typeof e) return { "default": e }; var t = _getRequireWildcardCache(r); if (t && t.has(e)) return t.get(e); var n = { __proto__: null }, a = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var u in e) if ("default" !== u && {}.hasOwnProperty.call(e, u)) { var i = a ? Object.getOwnPropertyDescriptor(e, u) : null; i && (i.get || i.set) ? Object.defineProperty(n, u, i) : n[u] = e[u]; } return n["default"] = e, t && t.set(e, n), n; }
function _interopRequireDefault(e) { return e && e.__esModule ? e : { "default": e }; }
// This is an application for private use only.

var loginValidationSchema = yup.object().shape({
  email: yup.string().email('Please enter a valid email').required('Email is required'),
  password: yup.string().min(6, function (_ref) {
    var min = _ref.min;
    return "Password must be at least ".concat(min, " characters");
  }).required('Password is required')
});
function Login() {
  var _useAuth = (0, _authContext.useAuth)(),
    token = _useAuth.token,
    user = _useAuth.user,
    saveToken = _useAuth.saveToken,
    saveUser = _useAuth.saveUser;
  var navigation = (0, _native.useNavigation)();
  return /*#__PURE__*/_react["default"].createElement(_reactNative.View, {
    style: styles.container
  }, /*#__PURE__*/_react["default"].createElement(_reactNative.Image, {
    source: _logo["default"],
    style: styles.logo
  }), /*#__PURE__*/_react["default"].createElement(_reactNative.Text, {
    style: styles.title
  }, "Login"), /*#__PURE__*/_react["default"].createElement(_formik.Formik, {
    validationSchema: loginValidationSchema,
    initialValues: {
      email: '',
      password: ''
    },
    onSubmit: submit
  }, function (_ref2) {
    var handleChange = _ref2.handleChange,
      handleBlur = _ref2.handleBlur,
      handleSubmit = _ref2.handleSubmit,
      values = _ref2.values,
      errors = _ref2.errors,
      touched = _ref2.touched,
      isValid = _ref2.isValid;
    return /*#__PURE__*/_react["default"].createElement(_react["default"].Fragment, null, /*#__PURE__*/_react["default"].createElement(_reactNative.View, {
      style: styles.inputContainer
    }, /*#__PURE__*/_react["default"].createElement(_Ionicons["default"], {
      name: "mail-outline",
      size: 25,
      style: styles.icon
    }), /*#__PURE__*/_react["default"].createElement(_reactNative.TextInput, {
      style: styles.input,
      placeholder: "Email",
      keyboardType: "email-address",
      onChangeText: handleChange('email'),
      onBlur: handleBlur('email'),
      value: values.email
    })), errors.email && touched.email && /*#__PURE__*/_react["default"].createElement(_reactNative.Text, {
      style: styles.errorText
    }, errors.email), /*#__PURE__*/_react["default"].createElement(_reactNative.View, {
      style: styles.inputContainer
    }, /*#__PURE__*/_react["default"].createElement(_Ionicons["default"], {
      name: "lock-closed-outline",
      size: 25,
      style: styles.icon
    }), /*#__PURE__*/_react["default"].createElement(_reactNative.TextInput, {
      style: styles.input,
      placeholder: "Password",
      secureTextEntry: true,
      onChangeText: handleChange('password'),
      onBlur: handleBlur('password'),
      value: values.password
    })), errors.password && touched.password && /*#__PURE__*/_react["default"].createElement(_reactNative.Text, {
      style: styles.errorText
    }, errors.password), /*#__PURE__*/_react["default"].createElement(_reactNative.TouchableOpacity, {
      onPress: function onPress() {
        return navigation.navigate('Forget');
      }
    }, /*#__PURE__*/_react["default"].createElement(_reactNative.Text, {
      style: styles.forgotPassword
    }, "Forgot Password?")), /*#__PURE__*/_react["default"].createElement(_reactNative.TouchableOpacity, {
      style: styles.button,
      onPress: handleSubmit,
      disabled: !isValid
    }, /*#__PURE__*/_react["default"].createElement(_reactNative.Text, {
      style: styles.buttonText
    }, "Login")), /*#__PURE__*/_react["default"].createElement(_reactNative.TouchableOpacity, {
      onPress: function onPress() {
        return navigation.navigate('SignUp');
      }
    }, /*#__PURE__*/_react["default"].createElement(_reactNative.Text, {
      style: styles.signUp
    }, "Don't have an account? ", /*#__PURE__*/_react["default"].createElement(_reactNative.Text, {
      style: styles.signUpLink
    }, "Sign Up"))));
  }));
}
var styles = _reactNative.StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#fff',
    paddingHorizontal: 20
  },
  logo: {
    height: 200,
    width: 200,
    resizeMode: 'contain',
    marginBottom: 20
  },
  title: {
    fontSize: 32,
    marginBottom: 40,
    fontWeight: 'bold',
    color: 'black'
  },
  inputContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    width: '100%',
    height: 50,
    backgroundColor: '#f1f1f1',
    borderRadius: 8,
    paddingHorizontal: 10,
    marginBottom: 20
  },
  icon: {
    marginRight: 10
  },
  input: {
    flex: 1,
    height: '100%'
  },
  forgotPassword: {
    alignSelf: 'flex-end',
    marginBottom: 20,
    color: '#000'
  },
  button: {
    width: '100%',
    height: 50,
    backgroundColor: '#1E90FF',
    borderRadius: 8,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 20
  },
  buttonText: {
    color: '#fff',
    fontSize: 18
  },
  signUp: {
    color: '#000'
  },
  signUpLink: {
    color: '#1E90FF'
  },
  errorText: {
    color: 'red',
    alignSelf: 'flex-start',
    marginBottom: 10
  }
});
