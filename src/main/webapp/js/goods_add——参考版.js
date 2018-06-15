/*******************************************************************************
 * 发布商品 (添加商品)
 * @author dylan_xu
 * @version 4.1.0 2011-2-12
*******************************************************************************/
// 初始化 httpClient 对象
var httpClient = new HttpClient("cdo_web_servlet.cdo");
var designer = new Object();
var richTxtPropArr = new Array();
var lDistrict_Id = 0;
var strBrand_Model = "";
var hasSKU = false;		//是否有SKU属性
var hasKey = false;		//是否有关键属性
var skuName = "";		//sku属性名
var keyName = "";		//key属性名
var curImg = null;		//当前图片

jQuery(document).ready(function() {
	initBasicInfoProducts();	//初始化商品基本信息(基本属性,关健属性,SKU属性)
	initChildDistrictList(lDistrict_Id);	//初始化地域信息
	initEvent();
	initCategoryPath();			//初始化类目路径
	initBrandList();			//供应商所经营的品牌范围
	initCargoTemplate();		//初始化商品运费模板
	initSubGoodsTable();
	initShopCategory();
	initRichTextPropertyList();
});

// 设置ajax调用全局参数
jQuery.ajaxSetup({
	contentType: "application/x-www-form-urlencoded; charset=UTF-8"
});

//初始化事件
function initEvent() {
	initValidatorGoods();
	//编辑分类
	jQuery('#edit_category').click(function(){
		editCategory();
	});
	
	//保存
	jQuery('#addGoodsSave').click(function() {
		addGoodsConfirm(true);
	});
	
	//保存并继续发布
	jQuery('#addGoodsSaveContinue').click(function() {
		addGoodsConfirm(false);
	});
	
	jQuery('[class^=input_]').live('mouseover', function() {
		jQuery(this).removeClass('input_out').addClass('input_over');
	}).live('mouseout', function() {
		jQuery(this).removeClass('input_over').addClass('input_out');
	});
	
	//计算totalAmount
	jQuery('[name=subGoodsAmount]').live('blur',function(){
		var totalAmount = 0;
		jQuery('[name^=inventory_]').each(function(){
			var amount = jQuery(this).find('[name=subGoodsAmount]').val();
			amount = amount!=''?amount:0;
			totalAmount = totalAmount + parseInt(amount);
		});
		jQuery('#totalGoods').val(totalAmount);
	});
	/*
	jQuery('#totalGoods').live('blur',function(){
		var totalGoods = jQuery('#totalGoods').val();
		jQuery('[name^=inventory_]').each(function(){
			var amount = jQuery(this).find('[name=subGoodsAmount]').val();
			if(amount == '')
			{
				jQuery(this).find('[name=subGoodsAmount]').val(totalGoods);
			}
		});
	});
	*/
	
	//确认新增商品
	jQuery('#cancelAddGoods').click(function() {
		cancelAddGoods();
	});

	//选择运费模板付费类型
	//买家承担
	jQuery('#buyer_fee').click(function(){
		jQuery('#cargoTemplateContent').show();
	});
	//卖家承担
	jQuery('#seller_fee').click(function(){
		jQuery('#goodsWeight').html("");
		jQuery('#cargoTemplateContent').hide();
	});
	
	
}

//初始化商品基本信息(基本属性,关健属性,SKU属性)
function initBasicInfoProducts()
{
	var lCategoryId = jQuery('#lCategoryId').val();
	cleanBasicInfoProducts();	//清除基本商品信息
	if(lCategoryId != null && lCategoryId != 0) {
		getNormalPropertyList(lCategoryId);	//获取类目的基本属性信息
		getKeyPropertyList(lCategoryId);	//获取类目的关键属性信息
		getSKUPropertyList(lCategoryId);	//获取类目的sku属性信息
	}
}
function cleanBasicInfoProducts()
{
	jQuery('#normalPropertyTable').html('');
	jQuery('#keyTd').remove();
	jQuery('#cdoKeylCategoryId').remove();
	jQuery('#subGoodsImgUpload').html('');
	jQuery('#keyPropertyTable').html('');
	jQuery('#skuTd').remove();
	jQuery('#cdoSKUlCategoryId').remove();
	jQuery('#skuPropertyTable').html('');
	jQuery('#subGoodsTable').html('');
	UPLOAD_IMG_STATE = true;	//上传状态
	hasSKU = false;
	hasKey = false;
	skuName = '';
	keyName = '';
	curImg = null;
}


//初始化子商品SKU列表
function initSubGoodsTable() {
	var html = '';
	//如果SKU和Key都不存在
	if(!hasSKU && !hasKey) {
		html += '<tr id="subGoods_0" style="display:none;">';
		html += '<td><table><tr name="inventory_0"></tr></table></td>';
		html += '</tr>';
	}
	
	//如果SKU存在，Key不存在
	if(hasSKU && !hasKey) {
		html += '<tr>';
		html += '<th width="18%">' + skuName + '</th>';
		html += '<th width="20%">价格（元）</th>';
		html += '<th width="20%">数量（件）</th>';
		html += '<th width="24%">商家编码</th>';
		html += '</tr>';
		html += '<tr id="subGoods_0">';
		html += '<td colspan="4" style="padding:0px;"><table name="skuTable" style="border:0px;width:100%;"></table></td>';
		html += '</tr>';
	}
	
	//如果SKU不存在，Key存在
	if(!hasSKU && hasKey) {
		html += '<tr>';
		html += '<th width="18%">' + keyName + '</th>';
		html += '<th width="20%">价格（元）</th>';
		html += '<th width="20%">数量（件）</th>';
		html += '<th width="24%">商家编码</th>';
		html += '</tr>';
		
		var str = '<tr><th width="13%">&nbsp;</th><th>自定义图片</th></tr>';
		jQuery('#subGoodsImgUpload').html(str);
	}
	
	//如果SKU存在，Key存在
	if(hasSKU && hasKey) {
		html += '<tr>';
		html += '<th width="18%">' + keyName + '</th>';
		
		html += '<th width="18%">' + skuName + '</th>';
		html += '<th width="20%">价格（元）</th>';
		html += '<th width="20%">数量（件）</th>';
		html += '<th width="24%">商家编码</th>';
		html += '</tr>';
		
		var str = '<tr><th width="13%">&nbsp;</th><th>自定义图片</th></tr>';
		jQuery('#subGoodsImgUpload').html(str);
	}
	
	jQuery('#subGoodsTable').html(html);
	chkKey();
}



//编辑分类
function editCategory()
{
	jQuery('#myForm').submit();
	return false;
}

//初始化类目路径
function initCategoryPath()
{
	var lCategoryId = jQuery('#lCategoryId').val();
	
	var cdoRequest = new CDO();
	cdoRequest.setStringValue("strTransName", "getCategory");
	cdoRequest.setStringValue("strServiceName", "CategoryService");
	cdoRequest.setLongValue("lCategoryId", lCategoryId);
	
	httpClient.handleTrans("getCategory", cdoRequest, true);
}

//初始化地域信息
function initChildDistrictList(lDistrictId)
{
	lDistrict_Id = lDistrictId;
	var cdoRequest = new CDO();
	cdoRequest.setStringValue("strTransName", "getChildDistrictList");
	cdoRequest.setStringValue("strServiceName", "DistrictService");
	cdoRequest.setLongValue("lDistrictId", lDistrictId);
	
	httpClient.handleTrans("getChildDistrictList", cdoRequest, true);
}

//获取城市
function getCityList(lDistrictId)
{
	if(lDistrictId == '-1') {
		jQuery('#city').html('<option value="-1">--请选择市区--</option>');
		return;
	}
	initChildDistrictList(lDistrictId);
}

//初始化品牌类表
function initBrandList()
{
	var lSellerId = jQuery('#lSellerId').val();
	var lCategoryId = jQuery('#lCategoryId').val();
	
	jQuery.ajax({
		type: 'post',
		url: rootPath + '/goods/goods!getOptionalBrandList.html',
		data: {'lCategoryId':lCategoryId,'lSellerId':lSellerId},
		dataType: 'json',
		success: function (rs) {
			if(rs.code == 0) {
				var data = eval('('+rs.data+')');
				if(data != null)
					createBrandList(data.cdoBrandList);
			}
		},
		async: false
	});
}
//创建品牌列表
function createBrandList(cdoBrandList)
{
	var nSellerType = jQuery('#nSellerType').val();
	
	var buffer = new StringBuffer();
	buffer.append("<li class=\"brand_name\"><span class=\"pro_tit\">品牌：");
	if(SELLER_TYPE_B == nSellerType)
	{
		buffer.append("<i>*</i>");
	}
	buffer.append("</span>");
	buffer.append("<select name=\"lBrandId\" id='lBrandId' onchange='getBrandModelList(this.value);'>");
	buffer.append("<option value='-1'>--请选择品牌--</option>");
	if(cdoBrandList != null)
	{
		jQuery(cdoBrandList).each(function(){
			var lBrandId = this.lBrandId;
			var strBrandName = this.strBrandName;
			buffer.append("<option value=\""+lBrandId+"\">"+strBrandName+"</option>");
		});
	}
	buffer.append("</select></li>");
	jQuery('#brand_list').html(buffer.toString());
}

//选择品牌获取型号集合
function getBrandModelList(lBrandId){
	
	var lCategoryId = jQuery('#lCategoryId').val();
	var cdoRequest = new CDO();
	cdoRequest.setStringValue("strTransName", "getCategoryBrandModelList");
	cdoRequest.setStringValue("strServiceName", "CategoryBrandService");
	cdoRequest.setLongValue("lBrandId", lBrandId);
	cdoRequest.setLongValue("lCategoryId", lCategoryId);
	cdoRequest.setIntegerValue("nPageSize", 0);
	cdoRequest.setIntegerValue("nPageIndex", 0);
		
	httpClient.handleTrans("getCategoryBrandModelList", cdoRequest, true);
}

//初始化商品运费模板
function initCargoTemplate()
{
	var lSellerId = jQuery('#lSellerId').val();
	var cdoRequest = new CDO();
	cdoRequest.setStringValue("strTransName", "getSellerCargoTemplateList");
	cdoRequest.setStringValue("strServiceName", "SellerService");
	cdoRequest.setLongValue("lSellerId", lSellerId);
	
	httpClient.handleTrans("getSellerCargoTemplateList", cdoRequest, true);
}
//获取设计师对象
function getDesigner(lDesignerId)
{
	var lCategoryId = jQuery('#lCategoryId').val();
	var cdoRequest = new CDO();
	cdoRequest.setStringValue("strTransName", "getDesigner");
	cdoRequest.setStringValue("strServiceName", "DesignerService");
	cdoRequest.setLongValue("lDesignerId", lDesignerId);
	
	httpClient.handleTrans("getDesigner", cdoRequest);
}
//初始化指定类目处于启用状态的富文本属性列表
function initRichTextPropertyList()
{
	var lCategoryId = jQuery('#lCategoryId').val();
	var cdoRequest = new CDO();
	cdoRequest.setStringValue("strTransName", "getRichTextPropertyList");
	cdoRequest.setStringValue("strServiceName", "PropertyService");
	cdoRequest.setLongValue("lCategoryId", lCategoryId);
	
	httpClient.handleTrans("getRichTextPropertyList", cdoRequest);
}

//根据品牌和型号获取商品
function getGoodsByBrandAndModel() {
	var lBrandId = jQuery('#lBrandId').val();	//品牌id
	var strBrandModel = jQuery('#strBrandModel').val();//型号
	UPLOAD_IMG_STATE = true; 	//上传状态
	if(strBrandModel == -1){
		initBasicInfoProducts();
		initSubGoodsTable();
		jQuery('#strBrandModel').parent().find('input').hide();
		return;
	}
	if(strBrandModel == TEMP_NUMBER_TYPE){
		initBasicInfoProducts();
		initSubGoodsTable();
		jQuery('#strBrandModel').parent().find('input').show();
		return;
	}else{
		jQuery('#strBrandModel').parent().find('input').hide();
	}
	if(!lBrandId || lBrandId.trim() == '' || lBrandId == 0) {
		return;
	}
	if(!strBrandModel || strBrandModel.trim() == '') {
		return;
	}
	
	jQuery.ajax({
		type: 'post',
		url: rootPath + '/goods/goods!getGoodsByBrandAndModel.html',
		data: {'lBrandId':lBrandId,'strBrandModel':strBrandModel},
		dataType: 'json',
		success: function (rs) {
			var data;
			if(rs.code == 0) {
				data = eval('('+rs.data+')');
				var cdoGoods = data.cdoGoods;
				initGoodsInformation(cdoGoods,true);
			}else{
				initBasicInfoProducts();
				initSubGoodsTable();
			}
		},
		async: false
	});
}

httpClient.onRequestOK=function(strTransName,cdoResponse,cdoReturn)
{
	var nCode = cdoReturn.getIntegerValue("nCode");
	switch(strTransName)
	{
		case "getCategory":
			var buffer = new StringBuffer();
			buffer.append("<strong>商品分类：</strong>");
			if( nCode == 0){
				buffer.append("<span>");
				var cdoCategory = cdoResponse.getCDOValue("cdoCategory");
				var nLevel = cdoCategory.getIntegerValue("nLevel");	//类目等级
				if(nLevel > 0)
				{
					buffer.append(cdoCategory.getStringValue("strCategoryName1"));
				}
				for (var i=2;i<=nLevel;i++)
				{
					buffer.append("<i>&gt;</i>");
					buffer.append(cdoCategory.getStringValue("strCategoryName"+i));
				}
				
				buffer.append("</span>");
				jQuery('#category_path').html(buffer.toString());
			}
			break;
		case "getChildDistrictList":
			if(nCode == 0)
			{
				var buffer = new StringBuffer();
				if(lDistrict_Id != 0)
				{
					buffer.append("<option value=\"-1\">--请选择市区--</option>");
				}
				var cdosChildDistrictList = cdoResponse.getCDOArrayValue("cdosChildDistrictList");
				if(cdosChildDistrictList != null)
				{
					if(cdosChildDistrictList instanceof Array)
					{
						jQuery(cdosChildDistrictList).each(function(){
							buffer.append("<option value='" + this.getLongValue("lId") + "'>");
							buffer.append(this.getStringValue("strName") + "</option>");
						});
					}
				}
				if(lDistrict_Id == 0)
				{
					jQuery('#province').append(buffer.toString());
				}
				else
				{
					jQuery('#city').html(buffer.toString());
				}
			}
			break;
		case "getSellerCargoTemplateList":
			if(nCode == 0)
			{
				var buffer = new StringBuffer();
				buffer.append('<option value="-1">--请选择运费模板--</option>');
				var cdosCargoTemplateList = cdoResponse.getCDOArrayValue('cdosCargoTemplateList');
				if(cdosCargoTemplateList != null)
				{
					jQuery(cdosCargoTemplateList).each(function(){
						var lCargoTemplateId = this.getLongValue("lCargoTemplateId");
						var strName = this.getStringValue("strName");
						var nFreightType = this.getIntegerValue("nFreightType");
						buffer.append("<option id=\"lCargoTemp_"+lCargoTemplateId+"\" nfreighttype=\""+nFreightType+"\" value=\""+lCargoTemplateId+"\">"+strName+"</option>");
					});
				}
				jQuery('#cargoTemplate').html(buffer.toString());
			}
			break;
		case "getCategoryBrandModelList":
			if(nCode == 0)
			{
				var nSellerType = jQuery('#nSellerType').val();
				var buffer = new StringBuffer();
				buffer.append('<li class="brand_name"><span class="pro_tit">型号：');
				if(SELLER_TYPE_B == nSellerType)
				{
					buffer.append("<i>*</i>");
				}
				buffer.append("</span>");
				buffer.append('<select id ="strBrandModel" name="strBrandModel" onchange="getGoodsByBrandAndModel();">');
				buffer.append("<option value='-1'>--请选择型号--</option>");
				var strEnabledModel = cdoResponse.getCDOValue('strEnabledModel');
				if(strEnabledModel != null)
				{
					var data = eval('('+strEnabledModel+')');
					if(data != null)
					{
						var str = data.data;
						if(str.length > 0);
						{
							for(var i=0;i<str.length;i++)
							{
								buffer.append("<option value='"+str[i]+"'>"+str[i]+"</option>");
							}
						}
						
					}
				}
				buffer.append("<option value='10000'>其他</option>");
				buffer.append('&nbsp;<input type="text" class="input_out" maxlength="255" style="display:none"/>');
				buffer.append('</select></li>');
				jQuery('#brandModelList').html(buffer.toString());
				jQuery('#strBrandModel').attr('value',strBrand_Model);
			}
			break;
		case "getDesigner":
			if(nCode == 0)
			{
				var cdoDesigner = cdoResponse.getCDOValue("cdoDesigner");
				if(cdoDesigner)
				{
					designer.lId = cdoDesigner.getIntegerValue("lId");
					designer.strCode = cdoDesigner.getStringValue("strCode");
					designer.nCategoryId = cdoDesigner.getIntegerValue("nCategoryId");
					designer.strName = cdoDesigner.getStringValue("strName");
					designer.strInitial = cdoDesigner.getStringValue("strInitial");
					designer.strAvatar = cdoDesigner.getStringValue("strAvatar");
					designer.strIntroduction = cdoDesigner.getStringValue("strIntroduction");
					designer.strData = cdoDesigner.getStringValue("strData");
				}
			}
			break;
		case "getRichTextPropertyList":
			if(nCode == 0)
			{
				var cdosRichTextPropList = cdoResponse.getCDOArrayValue("cdosRichTextPropList");
				buildRichTextPropList(cdosRichTextPropList);
			}
			break;
	}
	
}

//创建富文本属性列表
function buildRichTextPropList(cdosRichTextPropList)
{
	var i=0;
	jQuery('#goods_rich_txt').html("");
	if(cdosRichTextPropList != null)
	{
		if(cdosRichTextPropList instanceof Array)
		{
			jQuery(cdosRichTextPropList).each(function(){
				var buffer = new StringBuffer();
				var richTxt = new Object();
				richTxt.lCategoryId = this.getLongValue("lCategoryId");
				richTxt.nPropertyIndex = this.getIntegerValue("nPropertyIndex");
				richTxt.strPropertyName = this.getStringValue("strPropertyName");
				richTxtPropArr.push(richTxt);
				var name = "goods_rich_txt_" + richTxt.lCategoryId + "_" + richTxt.nPropertyIndex;
				
				var textarea = "<textarea id=\""+name+"_edit_id\" name=\""+name+"\" rows=\"20\" cols=\"120\" style=\"width:600px;height:200px;visibility:hidden;display:none;\"></textarea>";
				buffer.append("<span class=\"pro_tit\">"+ richTxt.strPropertyName +"：</span>");
				buffer.append("<div class=\"txt_box_2\">");
				buffer.append(textarea);
				buffer.append("</div>");
				buffer.append("");
				jQuery('#goods_rich_txt').append(buffer.toString());
				
				KE.init({
					id:name+"_edit_id",
					resizeMode:1
				});
				KE.create(name+"_edit_id");
			});
		}
		//jQuery('#goods_rich_txt').remove();
	}
}
//确认添加商品
function addGoodsConfirm(flg) {
	if(!validatorAddGoods(GOODS_ADD_TYPE))
	{
		return;
	}
	var goodsData = getGoodsInfo(GOODS_ADD_TYPE);
	var strDetail = KE.html('goods_detail_edit_id');	//商品详情
	strDetail = richTxtInputEncoder(strDetail);
	jQuery.ajax({
		type: 'post',
		url: rootPath + '/goods/goods!addGoodsConfirm.html',
		data: {'goodsData':goodsData,'strDetail':strDetail},
		dataType: 'json',
		success: function (rs) {
			if(rs.code == 0) {
				if(flg){
					var isIE=!!window.ActiveXObject; 
					var isIE6=isIE&&!window.XMLHttpRequest; 
					if (isIE){ 
						if (isIE6){
							alert('发布商品成功!'); 
							//window.location.href= rootPath + "/add_success.html";
							return; 
						}
					}
					jQuery('#redirectForm').submit();
				}else{
					alert('保存商品成功!');
				}
			} else {
				if(rs.code == -1) {
					alert(rs.message);
				} else {
					alert('保存失败，失败原因：' + rs.message);
				}
			}
		},
		async: false
	});
}



